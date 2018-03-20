package T0308.OOP;

import java.util.Random;

/**
 * Created by vip on 2018/3/20.
 */
public class RTTI {
    static void printInfo(Class cc) {
        System.out.println("Class name : " + cc.getName() +
                           ", is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: " + cc.getSimpleName());
        System.out.println("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        //RTTI 运行时类型信息、
        /*使用方式
        * 1.传统的， 编译时已经知道所有的类型，比如Shape e = (Shape)s1;
        * 2.反射 机制， 运行时发现，Class.forName()
        * 3.辅助 ， instanceof， 返回bool“你是这个类吗？或者是这个类的派生类吗？*/
        //class包含的方法
        Class c = null;
        try {
            c = Class.forName("T0308.OOP.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);

        for (Class face : c.getInterfaces()) {
            printInfo(face);
        }

        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Can't Instantiate");
            System.exit(1);
        } catch (IllegalAccessException ex) {
            System.out.println("Can't access");
            System.exit(1);
        }
        printInfo(obj.getClass());

        //初始化的区别
        Class intiable = Initable.class;
        System.out.println("After creating Initable ref");
        System.out.println(Initable.staticFinal);
        System.out.println(Initable.staticFinal2);
        System.out.println(Initable2.staticNonFinal);

        try {
            Class initable3 = Class.forName("T0308.OOP.Initable3");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find Initable3");
            System.exit(1);
        }
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticFonFinal);
    }

    //使用RTTI之前，首先要获取Class对象的引用，方式有：
    /*1.如果没有持有该类型的对象，Class.forName() 实现此功能
    * 2.如果已拥有一个感兴趣的类型的对象，那就可以通过调用getClass（）方法获取
    * 3.使用类字面常量 ， FancyToy.class; 这样做不仅更简单，而且更安全，因为它在编译时就会受到检查（因此不需要置于try语句块中），并且它根除了对forName方法的引用，所以也更高效
    * class.forName() 会自动初始化， 类字面常量不会自动初始化，在静态方法或非final静态域进行首次引用时初始化*/

    /*为了使用类做的准备工作：
    * 1、加载：由类加载器执行。查找字节码，并创建一个Class对象
    * 2. 链接：验证类中的字节码，为静态域分配存储空间，并且如果必须的话，解析这个类创建的对其他类的所有引用
    * 3. 初始化： 如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块*/
    public static Random rand = new Random(47);

    /*RTTI的限制？ 如何突破？ -- 反射机制*/
    /*限制-- 如果不知道某个对象的确切类型，RTTI可以告诉你，但是有一个限制，这两个类型在
    * 在编译时必须已知，这样才能使用RTTI识别它，也就是在编译时，编译器必须知道所有要通过RTTI来处理的类。*/



}


interface HasBatteries {}
interface WaterProof{}
interface Shoots{}

class Toy {
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Shoots,WaterProof {
    FancyToy() {
        super(1);
    }
}



class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = RTTI.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticFonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}