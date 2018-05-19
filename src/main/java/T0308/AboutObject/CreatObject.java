package T0308.AboutObject;

/**
 * Created by vip on 2018/3/8.
 */
public class CreatObject extends  A{
    /**
     * Class 和 Instance 概念 区别
     * class 类  Instance 实例
     * class 是模板，是蓝图，  instance 是类创造出来的一个对象
     */


    public CreatObject() {
        System.out.println("Test1的构造方法");
    }

    public static int k = print();

    public static int print() {
        System.out.println("utils.Test print");
        return 522;
    }

    /**
     * Instance 创建过程：
     * 1.无继承：分配内存空间，初始化变量，调用构造函数
     * 2.有继承：处理静态动作，分配内存空间，变量定义为初始值，
     * 从基类->子类，处理定义出的初始化，执行构造方法
     */
    public static void main(String[] args) {
        // Super s1 = new Sub();
        // Super s2 = new Super();
//        Sub s3 = new Sub();

        System.out.println("main start");
        CreatObject t1 = new CreatObject();
    }
    /**
     * 执行顺序大致分类：

     　　1.静态属性，静态方法声明，静态块。

     　　2.动态属性，普通方法声明，构造块。

     　　3.构造方法。
     */



    /**
     * important: 静态属性等从基类->子类进行初始化
     * 默认无参构造方法相关的特性
     */
}


class Super {

    static int a = getA();

    static {
        System.out.println("加载Super的静态块");
    }

    int b = getB();

    {
        System.out.println("加载Super的普通块");
    }

    Super() {
        System.out.println("加载Super的构造器");
    }

    static int getA() {
        System.out.println("加载Super的静态变量");
        return 1;
    }

    static int getB() {
        System.out.println("加载Super的实例变量");
        return 2;
    }

}

class Sub extends Super {

    static int c = getC();

    static {
        System.out.println("加载Sub的静态块");
    }



    {
        System.out.println("加载Sub的普通块");
    }

    Sub() {
        System.out.println("加载Sub的构造器");
    }

    static int getC() {
        System.out.println("加载Sub的静态变量");
        return 1;
    }

    static int getD() {
        System.out.println("加载Sub的实例变量");
        return 2;
    }
    int d = getD();
}

class A {
    public A() {
        System.out.println("A的构造方法");
    }

    public static int j = print();

    public static int print() {
        System.out.println("A print");
        return 521;
    }
}

 class Test1 extends A {


}