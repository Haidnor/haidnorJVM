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
package haidnor.vm.bcel.generic;

import haidnor.vm.bcel.Const;

/**
 * IF_ACMPNE - Branch if reference comparison doesn't succeed
 *
 * <PRE>
 * Stack: ..., value1, value2 -&gt; ...
 * </PRE>
 */
public class IF_ACMPNE extends IfInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used otherwise.
     */
    IF_ACMPNE() {
    }

    public IF_ACMPNE(final InstructionHandle target) {
        super(Const.IF_ACMPNE, target);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of implemented interfaces first, then call
     * methods according to the class hierarchy in descending order, i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPNE(this);
    }

    /**
     * @return negation of instruction
     */
    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(super.getTarget());
    }
}
