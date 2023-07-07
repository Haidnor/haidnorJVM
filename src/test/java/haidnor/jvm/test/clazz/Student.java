package haidnor.jvm.test.clazz;

public class Student extends Person{
    @Override
    public void eat() {
        System.out.println("学生只能在学校食堂吃饭");
    }
}
