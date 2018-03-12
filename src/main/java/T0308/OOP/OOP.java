package T0308.OOP;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vip on 2018/3/8.
 */
public class OOP {
    /**
     * 三大特性，概念、特性体现、使用场景：
     * 封装：当你使用其他人实现的对象并在对象上调用方法时，不需要知道底层是怎么做的，
     * 继承：
     * 多态：
     */

    /**
     * 静态多分派，动态单分派 概念
     */

    /**
     * 重载的概念和使用
     */

    /**
     * 继承：接口多实现，基类单继承（单继承多实现）
     */

    /**
     * 抽象，抽象类，接口
     */

    /**
     * 多态：方法覆盖的概念和使用
     */

    /**
     * 接口回调
     */

    /*Accessor访问器  Mutator更改器*/
    /*如果一个方法改变了调用它的对象，我们就说这是一个更改器方法，ArrayList类的add方法
    * 如果方法不改变调用自己的对象，它就是访问器方法
    * 修改有风险，并发访问；类只提供访问器方法，使对象都是不可变的*/

    /*java 中，变量只能持有对象的引用*/
    private int anInt;//如果没有初始化，则实例变量会用默认值初始化。局部变量必须显式初始化。
    public OOP(int i) {
        this.anInt = i;
    }

    public OOP() {
        this(1);//调用构造函数OOP（i），调用构造函数一定是第一条语句。
        //其他声明
    }

    public static final Random generator = new Random();//通过这种方式共享随机数产生器

    public static final ArrayList<Integer> expirationYear = new ArrayList<>();
    //表示对象的引用不变，特别是expirationYear不能为null；但是可以修改对象本身，
    public void order(){
        /*实例变量初始化和初始化块以他们在类中出现的先后顺序执行，并在构造函数方法体之前执行*/
        /*final 实例变量必须在所有的构造函数末尾初始化。 */


        /*类中变量声明为Static，那么该变量属于类，而不是对象，每个类只有一个；
        * 每个对象都有一份实例变量的拷贝*/

        /* static 强调只有一个；final强调是一个常数；
        static final  表示一旦给定值，就不能修改，且可以通过类名访问，System.out是例外*/

    }


    //类第一次加载时，执行静态初始化块。静态变量默认值为0，false，null。
    //所有的静态变量初始化和静态初始化块以它们在类中声明中出现的顺序执行。

    //实例变量
    private int y;
    //静态变量
    private static int x;
    //静态初始化块,完成初始化工作。
    static {
        int year = LocalDate.now().getYear();
        for (int i = year; i <= year + 20; i++) {
            expirationYear.add(i);
        }
    }

    //静态方法，不用运行在对象上的方法,不能访问实例变量，但是可以访问本类中的静态变量。
    public static void init() {
        //常见使用就是工厂方法，也就是返回同一个类的新实例的静态方法。
        NumberFormat currFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percFormatter = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(currFormatter.format(x));
        System.out.println(percFormatter.format(x));
        //为什么不用构造函数代替？
        // 区分两个构造函数唯一的方法是它们的参数类型，因此不能有两个无参的构造函数。
        //另外，构造函数只能返回构造函数对应对象；工厂方法还能返回子类对象。
        //工厂方法还能返回共享对象，无须每次构建一个新对象。

    }

    //package， 包不能嵌套，java.util和java.util.regex没有任何关系。
    //包声明位于第一行。javac -d 运行时带上-d 选项会让class文件产生在单独的目录中。

    //jar归档或将程序打包
   /* jar cvfe program.jar com.mycompany.MainClass com/mycompany*//*.class
    然后运行
    java -jar program.jar*/

   /* 类路径：当你使用JAR文件时，需要指定class path 告诉编译器和虚拟机JAR 文件在哪。
    class path 包含：
    包含class文件的目录；
    JAR文件；
    包含JAR文件的目录；
    java -classpath .:../libs/libl.jar:../libs/lib2.jar com.mycompany.MainClass
    . 当前目录, : 分隔符 （Windows 中用 ;）
    很多JAR文件时：
    java -classpath .:../libs/\* com.mycompany.MainClass
    类无法找到时，考虑类路径设置。包括环境变量，和bash脚本。
    */



   //嵌套类  static
    private static class Item {
        String description;
        int quantity;
        double unitPrice;
   }

   //没有private限制的话， 嵌套类和普通声明的类没有任何区别
   private ArrayList<Item> items = new ArrayList<>();
   //外部类构建内部类的方法
    public void addItem(String description, int quantity, double unitPrice) {
        Item newItem = new Item();
        newItem.description = description;
        newItem.quantity = quantity;
        newItem.unitPrice = unitPrice;
        items.add(newItem);
    }

    //内部类  没有static,含有自己外部类对象的引用
    //当嵌套类的实例不需要知道它属于外部类的哪个实例是，使用静态嵌套类。
    //不能声明静态成员。
    public class Member {
        private String name;
        private ArrayList<Member> friends;
        public Member(String name) {
            this.name = name;
            friends = new ArrayList<>();
        }

        public void leave() {
            members.remove(this);
            //可以引用外部类的实例变量和方法。
            // 即outer.members.remove(this);
            // OOP.this.members.remove(this);
        }

        public boolean belongsTo(OOP n) {
            return OOP.this == n;//需要显式表明外部类引用。
        }

    }

    private ArrayList<Member> members;//不

    public Member enroll(String name) {
        Member newMember = new Member(name);// = this.new Member(name);
        members.add(newMember);
        return newMember;
    }
    OOP myFace = new OOP();
    OOP.Member wilma = myFace.new Member("Wilma");
    //可以在外部类的任何实例上调用内部类构造函数

}
