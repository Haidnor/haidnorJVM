package haidnor.jvm.test.instruction.references;

import haidnor.jvm.test.instruction.loads.Student;

public class NEW {

    public static void main(String[] args) {
        Student student = new Student((byte) 1, (short) 2, 100.00f, 200.1D, 20000, 30000L, 'a', true);
        System.out.println(student.byteVal);
        System.out.println(student.shortVal);
        System.out.println(student.floatVal);
        System.out.println(student.doubleVal);
        System.out.println(student.intVal);
        System.out.println(student.longVal);
        System.out.println(student.charVal);
        System.out.println(student.booleanVal);
    }

}
