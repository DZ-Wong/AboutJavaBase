package T0308.Jvm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vip on 2018/3/22.
 */
public class Loader {
    /*
    * 重写自己的ClassLoader，需要重写findClass方法来搜索类*/

    /*
    JVM 根据类名+包名+ClassLoader实例ID来判定两个类是否相同，是否已加载过
     */


    /*当某个classloader加载的所有类实例化的所有对象都被回收了，则该classloader会被回收。*/

    /**
     * 父类ClassLoader加载
     * @throws Exception
     */
    public static void test() throws Exception{
        MyClassLoader loader = new MyClassLoader();
        //委托给APPClassLoader加载，所以可以访问到
        Class<?> c = loader.loadClass("T0308.Jvm.HRHandsome");
        System.out.println("Loader by : " + c.getClassLoader());

        Person p = (Person) c.newInstance();
        p.say();

        HRHandsome man = (HRHandsome) c.newInstance();
        man.say();
    }

    /*
    根据class loader命名空间规则，每个class loader都有自己唯一的命名空间，
    每个class loader 只能访问自己命名空间中的类，
    如果一个类是委托parent加载的，那么加载后，这个类就类似共享的，
    parent和child都可以访问到这个类，因为parent是不会委托child加载类的，
    所以child加载的类parent访问不到。
    简单来说，即子加载器的命名空间包含了parent加载的所有类，反过来则不成立。
     */
    //由于外围的Loader类是APPClassLoader加载的，所以它看不见有MyClassLoader类加载的HRHandsome类。

    public static void test2() throws Exception{
        MyClassLoader loader = new MyClassLoader();
        Class<?> c = loader.findClass("T0308.Jvm.Loader");
        System.out.println("Loader by : " + c.getClassLoader());

        Person p = (Person) c.newInstance();
        p.say();
        //注释下面两行则不报错
//        HRHandsome man = (HRHandsome) c.newInstance();
//        man.say();
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            test2();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "test2");
        }

        test4();
    }

    /*
    * Class.forName 是显式加载类，作用是要求JVM查找并加载指定的类，
    * 也就是执行该类的静态代码段。 所以这里的加载驱动是为了触发Driver类中的注册的静态方法。*/
    public void DbTest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
//            conn = DriverManager.getConnection(url)
            //.....
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*一个class可以被不同的class loader重复加载，但是同一个class只能被同一个ClassLoader加载一次。*/
    public static void test4() {
        ClassLoader c1 = Loader.class.getClassLoader();
        Loader test = new Loader();
        ClassLoader c2 = Loader.class.getClassLoader();
        System.out.println("c1.equals(c2): " + c1.equals(c2));
    }
}


//放在同一个文件中，则这个类只被加载一次。
interface Person{
    public void say();
}

class HRHandsome implements Person {
    @Override
    public void say() {
        System.out.println("High Rich Handsome");
    }
}

class MyClassLoader extends ClassLoader {
    /*
    覆盖了父类的findClass，实现自定义的classLoader
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bt = loadClassData(name);
        return defineClass(name, bt, 0, bt.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(
            className.replace(".", "/" ) + ".class"
        );
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        int len = 0;
        try {
            while ((len = is.read()) != -1) {
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteSt.toByteArray();
    }
}