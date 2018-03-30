package T0308.AboutObject;

/**
 * 加载顺序。
 * Created by vip on 2018/3/30.
 */
public class TestOrder {
    public static int k = 0;
    public static TestOrder t1 = new TestOrder("t1");
    public static TestOrder t2 = new TestOrder("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }
    static {
        print("静态块");
    }

    public TestOrder(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
        TestOrder t = new TestOrder("init");

        Student stu = new Student("zhangan", 21);
//       此句加载分为四部分
        /**
         1.堆内存不赋值， 栈内存分配给name 和 age，初始值为null 和 0；
         2.堆内存不赋值，栈内存name 和 age 赋值为 “李寻欢” 和 20
         3.堆内存不赋值， 栈内存 name 和 age 赋值为 zhangan  和 21；
         4.堆内存为str对象分配内存， 栈内存不变
         */
    }
}


/**
 总结：只要按照这个步骤，遇到这一类问题就可以解决了。



 　　　　  1-3：类加载过程，不涉及构造方法

 　　　　1-5: 实例化过程，涉及构造方法

 　　1.类中所有属性的默认值（一举而成）

 　　2. 父类静态属性初始化，静态块，静态方法的声明（按出现顺序执行）

 　　3. 子类静态属性初始化，静态块，静态方法的声明 （按出现顺序执行）

 　　4.  调用父类的构造方法，

 　　　　　　首先父类的非静态成员初始化，构造块，普通方法的声明（按出现顺序执行）

 　　　　　　然后父类构造方法

 　　5.  调用子类的构造方法，

 　　　　　　首先子类的非静态成员初始化，构造块，普通方法的声明（按出现顺序执行）

 　　　　　　然后子类构造方法



 　　　（注意：类加载过程中，可能调用了实例化过程（因为static可以修饰方法，属性，代码块，内部类），
 此时则会暂停类加载过程而先执行实例化过程（被打断），
 执行结束再进行类加载过程，
 上面就是典型的暂停类加载。
 */



class Student {
    public String name = "李寻欢";
    public int age = 20;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

}