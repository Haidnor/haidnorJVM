/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package haidnor.vm.bcel.verifier;

import haidnor.vm.bcel.Repository;
import haidnor.vm.bcel.classfile.JavaClass;
import haidnor.vm.bcel.classfile.Utility;
import haidnor.vm.bcel.verifier.statics.Pass1Verifier;
import haidnor.vm.bcel.verifier.statics.Pass2Verifier;
import haidnor.vm.bcel.verifier.statics.Pass3aVerifier;
import haidnor.vm.bcel.verifier.structurals.Pass3bVerifier;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Verifier instance is there to verify a class file according to The Java Virtual Machine Specification, 2nd Edition.
 * <p>
 * Pass-3b-verification includes pass-3a-verification; pass-3a-verification includes pass-2-verification;
 * pass-2-verification includes pass-1-verification.
 * <p>
 * A Verifier creates PassVerifier instances to perform the actual verification. Verifier instances are usually
 * generated by the VerifierFactory.
 *
 * @see VerifierFactory
 * @see PassVerifier
 */
public class Verifier {

    static final String NAME = "Apache Commons BCEL";
    static final String BANNER = NAME + "\nhttps://commons.apache.org/bcel\n";

    static final Verifier[] EMPTY_ARRAY = {};
    /**
     * The name of the class this verifier operates on.
     */
    private final String className;
    /**
     * The Pass3aVerifiers for this Verifier instance. Key: Interned string specifying the method number.
     */
    private final Map<String, Pass3aVerifier> p3avs = new HashMap<>();
    /**
     * The Pass3bVerifiers for this Verifier instance. Key: Interned string specifying the method number.
     */
    private final Map<String, Pass3bVerifier> p3bvs = new HashMap<>();
    /**
     * A Pass1Verifier for this Verifier instance.
     */
    private Pass1Verifier p1v;

    /**
     * A Pass2Verifier for this Verifier instance.
     */
    private Pass2Verifier p2v;

    /**
     * Instantiation is done by the VerifierFactory.
     *
     * @see VerifierFactory
     */
    Verifier(final String fullyQualifiedClassName) {
        className = fullyQualifiedClassName;
    }

    /**
     * Verifies class files. This is a simple demonstration of how the API of BCEL's class file verifier "JustIce" may be
     * used. You should supply command-line arguments which are fully qualified namea of the classes to verify. These class
     * files must be somewhere in your CLASSPATH (refer to Sun's documentation for questions about this) or you must have
     * put the classes into the BCEL Repository yourself (via 'addClass(JavaClass)').
     */
    public static void main(final String[] args) {
        System.out.println(BANNER);
        for (int index = 0; index < args.length; index++) {
            try {
                if (args[index].endsWith(JavaClass.EXTENSION)) {
                    final int dotclasspos = args[index].lastIndexOf(JavaClass.EXTENSION);
                    if (dotclasspos != -1) {
                        args[index] = args[index].substring(0, dotclasspos);
                    }
                }
                args[index] = Utility.pathToPackage(args[index]);
                System.out.println("Now verifying: " + args[index] + "\n");
                verifyType(args[index]);
                Repository.clearCache();
                System.gc();
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static void verifyType(final String fullyQualifiedClassName) throws ClassNotFoundException {
        final Verifier verifier = VerifierFactory.getVerifier(fullyQualifiedClassName);
        VerificationResult verificationResult;
        verificationResult = verifier.doPass1();
        System.out.println("Pass 1:\n" + verificationResult);
        verificationResult = verifier.doPass2();
        System.out.println("Pass 2:\n" + verificationResult);
        if (verificationResult == VerificationResult.VR_OK) {
            final JavaClass jc = Repository.lookupClass(fullyQualifiedClassName);
            for (int i = 0; i < jc.getMethods().length; i++) {
                verificationResult = verifier.doPass3a(i);
                System.out.println("Pass 3a, method number " + i + " ['" + jc.getMethods()[i] + "']:\n" + verificationResult);
                verificationResult = verifier.doPass3b(i);
                System.out.println("Pass 3b, method number " + i + " ['" + jc.getMethods()[i] + "']:\n" + verificationResult);
            }
        }
        System.out.println("Warnings:");
        final String[] warnings = verifier.getMessages();
        if (warnings.length == 0) {
            System.out.println("<none>");
        }
        for (final String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("\n");
        // avoid swapping.
        verifier.flush();
    }

    /**
     * Returns the VerificationResult for the given pass.
     */
    public VerificationResult doPass1() {
        if (p1v == null) {
            p1v = new Pass1Verifier(this);
        }
        return p1v.verify();
    }

    /**
     * Returns the VerificationResult for the given pass.
     */
    public VerificationResult doPass2() {
        if (p2v == null) {
            p2v = new Pass2Verifier(this);
        }
        return p2v.verify();
    }

    /**
     * Returns the VerificationResult for the given pass.
     *
     * @param methodNo The method to verify
     * @return the VerificationResult
     */
    public VerificationResult doPass3a(final int methodNo) {
        return p3avs.computeIfAbsent(Integer.toString(methodNo), k -> new Pass3aVerifier(this, methodNo)).verify();
    }

    /**
     * Returns the VerificationResult for the given pass.
     *
     * @param methodNo The method to verify
     * @return the VerificationResult
     */
    public VerificationResult doPass3b(final int methodNo) {
        return p3bvs.computeIfAbsent(Integer.toString(methodNo), k -> new Pass3bVerifier(this, methodNo)).verify();
    }

    /**
     * Forget everything known about the class file; that means, really start a new verification of a possibly different
     * class file from BCEL's repository.
     */
    public void flush() {
        p1v = null;
        p2v = null;
        p3avs.clear();
        p3bvs.clear();
    }

    /**
     * Returns the name of the class this verifier operates on. This is particularly interesting when this verifier was
     * created recursively by another Verifier and you got a reference to this Verifier by the getVerifiers() method of the
     * VerifierFactory.
     *
     * @see VerifierFactory
     */
    public final String getClassName() {
        return className;
    }

    /**
     * This returns all the (warning) messages collected during verification. A prefix shows from which verifying pass a
     * message originates.
     *
     * @throws ClassNotFoundException if this class can't be found.
     */
    public String[] getMessages() throws ClassNotFoundException {
        final List<String> messages = new ArrayList<>();
        if (p1v != null) {
            p1v.getMessagesList().forEach(element -> messages.add("Pass 1: " + element));
        }
        if (p2v != null) {
            p2v.getMessagesList().forEach(element -> messages.add("Pass 2: " + element));
        }
        for (final Pass3aVerifier pv : p3avs.values()) {
            final int meth = pv.getMethodNo();
            for (final String element : pv.getMessages()) {
                messages.add("Pass 3a, method " + meth + " ('" + Repository.lookupClass(className).getMethods()[meth] + "'): " + element);
            }
        }
        for (final Pass3bVerifier pv : p3bvs.values()) {
            final int meth = pv.getMethodNo();
            for (final String element : pv.getMessages()) {
                messages.add("Pass 3b, method " + meth + " ('" + Repository.lookupClass(className).getMethods()[meth] + "'): " + element);
            }
        }

        return messages.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
    }
}
