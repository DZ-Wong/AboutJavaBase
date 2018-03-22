package T0308.Jvm;

/**
 * Created by vip on 2018/3/22.
 */
public class JvmDemo {
    /*
    java运行时数据分类

1.程序计数器：是一个数据结构，用于保存当前正常执行的程序的内存地址。Java虚拟机的多线程就是通过线程轮流切换并分配处理器时间来实现的，为了线程切换后能恢复到正确的位置，每条线程都需要一个独立的程序计数器，互不影响，该区域为“线程私有”。
2.Java虚拟机栈：线程私有的，与线程生命周期相同，用于存储局部变量表，操作栈，方法返回值。局部变量表放着基本数据类型，还有对象的引用。
3.本地方法栈：跟虚拟机栈很像，不过它是为虚拟机使用到的Native方法服务。
4.Java堆：所有线程共享的一块内存区域，对象实例几乎都在这分配内存。
5.方法区：各个线程共享的区域，储存虚拟机加载的类信息，常量，静态变量，编译后的代码。
6.运行时常量池：代表运行时每个class文件中的常量表。包括几种常量：编译时的数字常量、方法或者域的引用。
     */


    /*
    查看父类加载器,
    实际上ExtClassLoader的父类是null，但是当ClassLoader的loadClass方法中，
    parent为null时，是交给bootstrapClassLoader来处理的，这就使其看起来是extclassloader的父类。
     */
    public static void seeClassLoaderParent() {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类装载器： " + appClassLoader);

        ClassLoader extensionClassLoader = appClassLoader.getParent();
        System.out.println("系统类装载器的父类---扩展加载器： " + extensionClassLoader);

        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println("扩展类加载器父类---" + bootstrapClassLoader);
    }

    public static void main(String[] args) {
        seeClassLoaderParent();
    }
}
