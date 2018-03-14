package T0308.RunJava;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by vip on 2018/3/8.
 */
public class AboutRun {
    /**
     * javac 编译java文件为class文件过程，命令
     */

    /**
     * java 命令的使用，带package的java类如何在命令行中启动
     */

    /**
     * java程序涉及到的各个路径（classpath， java.library .path , java运行的主目录等);
     */

    /**
     *
     */



    public void Run()
        throws ClassNotFoundException, ReflectiveOperationException, MalformedURLException {
        //运行时类型信息和资源

        Object obj = new Object();
        Class<?> c1 = obj.getClass();//getClass返回一个称为Class类的对象
        //<?> 涉及变量的表达式中的类型检查
        //一旦有了Class 对象，就可以查出类名称
        System.out.println("This Object is an instance of " + c1.getName());

        //获取class 对象的另一个方法
        String className = "java.util.Scanner";
        Class<?> c2 = Class.forName(className);//用于构造那些在编译时不被知道的类的Class对象。
        //如果已知需要哪个类，则用下面的
        Class<?> c3 = java.util.Scanner.class;

       // Class 对象能做什么？
        //可以根据模糊对象类型构建实例对象。

        //资源加载
        //定位应用程序可能需要的资源，比如和类文件放在同一位置的配置文件或者图片
      //  InputStream stream = AboutRun.class.getResourceAsStream("config.txt");
      //  Scanner in = new Scanner(stream);
        //资源目录可以有子目录，可以是相对路径，也可以是绝对路径

//        class.getResourceAsStream("/config/menus.txt");

        //类加载器
        /*虚拟机指令存储在类文件， 类加载器负责加载字节流，并在虚拟机中将它们转化为一个类或者接口*/
        /*
           bootstrap 类加载器会加载Java类库（jre/lib/rt.jar）,它是虚拟机的一部分
          //没有对应ClassLoader 对象
          String.class.getClassLoader()  == null

           扩展加载器 从jre/lib/ext 目录中加载标准库扩展部分

           系统类加载器 加载应用程序类。 它定位 classpath中目录和JAR文件的类。
           //对应URLClassLoader 类的实例
         */

        /*
        * 知道应用程序的类路径， 可以加载相对路径上的其他文件
         */
        URL[] url = ((URLClassLoader) AboutRun.class.getClassLoader() ).getURLs();
        System.out.println(url +";\n" + url.toString());

        /*
            通过创建自己的URLClassLoader实例，可以从classpath以外的目录或者JAR文件中加载类
         */
        URL[] usls = {
            new URL("file://path/to/directory"),
            new URL("file://path/to/jarfile.jar")
        };
        String className2 = "T0308.RunJava.AboutRun";
        try (URLClassLoader loader = new URLClassLoader(usls)) {
            Class<?> cl = Class.forName(className, true, loader);
            //构建实例。。。



            loader.loadClass(className);//这个方法有缺陷，不加载静态初始化代码块

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*如果某个方法动态加载类，并且调用该方法的类又是被另一个类加载器加载的，就会有类加载器反转现象
        *  其中一种解决方法是：使用当前线程的上下文类加载器
        * */
//        Thread t = Thread.currentThread();
//        ClassLoader loader = t.getContextClassLoader();
//        Class<?> c12 = Class.forName(className, true, loader);

        //方法调用

        //构造对象 -- 调用无参构造函数
        Object obj1 = c1.newInstance();

        //构造对象 -- 调用其他构造函数,先找到Constructor对象,
        Constructor constr = c1.getConstructor(int.class);//int 对应构造函数的参数类型
        obj1 = constr.newInstance(42);




    }

    //使用数组  复制方法
    public static Object goodCopyOf(Object array, int newLength) {
        Class<?> c1 = array.getClass();
        if (!c1.isArray()) return null;//判断是否是数组
        Class<?> componentType = c1.getComponentType();//获取数组元素类型的Class
        int length = Array.getLength(array);
        Object newArray = Array.newInstance(componentType, newLength);
        for (int i = 0; i < Math.min(length, newLength); i++)
            Array.set(newArray, i, Array.get(array, i));
        return newArray;

    }

    //代理 Proxy类 可以在运行时创建实现了给定的接口或者接口集的新类。只有在编译时还不如道要实现哪一个接口时，才需要这样的代理
    //注意不能用newInstance 方法， -- 接口不能实例化
    //有什么作用？




    public static void main(String[] args) {
//        AboutRun m = new AboutRun();
//        try {
//            m.Run();
//        } catch (ReflectiveOperationException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        int[] primes = {2, 3, 4, 5, 11};
        primes = (int[]) goodCopyOf(primes, 10);
        System.out.println(Arrays.toString(primes));
        //int[] 是一个Object  ， 不是对象数组

    }




}
