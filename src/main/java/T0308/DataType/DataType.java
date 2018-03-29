package T0308.DataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by vip on 2018/3/8.
 */
public class DataType {

    /**
     * 基本类型，对应的对象类型
     */
    public static strictfp void dataType() {
        //整形
        int inta = 1_000_000; System.out.println("sizeof int is 4 byte; min is " + Integer.MIN_VALUE + "; max is "+ Integer.MAX_VALUE);
        long longb = 1_000_000L; System.out.println("sizeof long is 8 byte; min is " + Long.MIN_VALUE+ "; max is " + Long.MAX_VALUE);
        short shortc = 3; System.out.println("sizeof short is 2 byte; min is "+ Short.MIN_VALUE + "; max is " + Short.MAX_VALUE);
        byte byted = (byte)254; System.out.println("sizeof byte is 1 byte; min is " + Byte.MIN_VALUE + "; max is " + Byte.MAX_VALUE);


        //浮点型
        float floatg = 2.0F; System.out.println("sizeof float is 4 byte; min is "+ Float.MIN_VALUE + "; max is " + Float.MAX_VALUE);
        double doubleh = 3.0D; System.out.println("sizeof Double is 8 byte; min is " + Double.MIN_VALUE + "; max is " + Double.MAX_VALUE);

        char chare = 'J'; /* UTF-16字符编码中的编码单元 */

        boolean booleanf; //不是数字类型，与整数 0 1 没有关系。

        //ps(进制表达)：十六进制：0xABC； 二进制：0b1001； 八进制(前缀为0)：011
        //不需要符号，且需要多一个bit位，可以转为无符号数
//        System.out.println(Byte.toUnsignedInt(byted));
        /*可以用十六进制表示浮点数*/double afloat = 0x1.0p-10;
//        Double.POSITIVE_INFINITY - 正无穷1.0/0.0
//        Double.NEGATIVE_INFINITY - 负无穷
//        Double.NaN - 非数值， 0.0/0.0的结果
        if (Double.isNaN(doubleh)) {/*用来判断数值是否是NaN ， 不能用  x == Double.NaN 这种方式 */}
        if (Double.isInfinite(doubleh)) {/*判断是否是正负无穷*/}
        if (Double.isFinite(doubleh)) {/*用来判断既不是无穷也不是NaN*/}
        //1/10 没有精确的二进制表示， 1/3没有精确的十进制表示，精确计算用BigDecimal 类

      //常量，final- 大写；System.out 中out是例外  final String ABC = "abc"; public static final PrintStream out;
//        final 变量可以延迟初始化
        System.out.println("3 + 4 << 5 = " + (3 + 4 << 5));

        int a = 1;
        try {
            int b = a/0; //整数有异常
        } catch (ArithmeticException ex ) {
            System.out.println(ex);
        }

        double c = 2.0D;
        double d = c/0;//浮点没有异常


    }


    public static void turn() {
        //没有信息损失，合法
//        byte -> short 、 int 、 long 、double;
//        short / char  -> int、 long 、 double;
//        int -> long 、 double;
        //合法，但有信息损失
//        int -> float;
//        long -> float, double;

        int n = 42;
        String str = Integer.toString(n);
        String str2 = Integer.toString(n, 2);

        System.out.println( str  + ";"  + str2);

        n = Integer.parseInt(str);
        n = Integer.parseInt(str2, 2);

        String str3 = Double.toString(3.14);
        double x = Double.parseDouble("3.14");



    }

    public static void defaultValue() {
//        int long short char float double byte  - 0
//        Boolean -false
//        object - null


        int[] a =  new int[0] ;
        int[] b = new int [] {};
    }
    public static final String EFG = "efg";

    /**
     * 基本类型到对象类型的转换，装箱和拆箱
     */

    public static void arrayList() {
       /* 数组array 空间大小固定； 存储内容为基本类型和对象；可以下标访问；
        数组列表ArrayList 动态增长； 只能存对象类型；*/

        ArrayList<String> friends = new ArrayList<>();
        friends.add("peter");
        friends.add("paul");
        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }
        friends.remove(1);
        String first = friends.get(0);

        friends.add(0, "wdz");
        friends.set(1, "dzw");

        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }

        int[] names = new int[] {1, 2, 3, 4, 5};
        for (int i : names) {/* 数组遍历，类型与数组一致*/}
        for (String s : friends) {/* 数组列表遍历*/}

        //数组复制
        int[] anothernames = names;/* 这样复制，共享数组，一个改变另一个也改变*/

        /* 下面这种复制 仅仅复制值，不共享*/
        int[] othernames = Arrays.copyOf(names, names.length);


        //数组列表的复制
        ArrayList<String> people = friends;/* 共享内存，牵一发动全身*/
        people.set(0, "mary");
        String f0 = friends.get(0);
        System.out.println(f0);
        ArrayList<String> copyfriends = new ArrayList<>(friends);
        copyfriends.set(0, "father");
        String f1 = friends.get(0);
        System.out.println(f1);


        /* 构造函数初始化 、 复制*/
        String[] peo = new String[]{"abc", "bcd", "efg"};
        ArrayList<String> anotherPeople =
            new ArrayList<>(Arrays.asList(peo));

        ArrayList<String> otherpeople =
            new ArrayList<>(Arrays.asList("abc", "efg", "def"));

        String[] ple = otherpeople.toArray(new String[0]);

        /* 数组和数组列表的填充*/
        Arrays.fill(names, 0); /* int[] 数组*/
        Collections.fill(friends, ""); /* ArrayList<String>*/

        /* 数组和数组列表排序*/
        Arrays.sort(names);  Arrays.parallelSort(names);//后一种为在数组较大时会将工作分布到多个处理器上运行
        Collections.sort(friends);

        /* 打印输出*/
        String elements0 = Arrays.toString(names);
        String elements = friends.toString();

        /*数组列表反转元素和打乱元素*/
        Collections.reverse(friends);
        Collections.shuffle(friends);


        /* 多维数组遍历*/
        int[] [] triangle = {
            {1, 2, 3, 4},
            {5, 6, 7},
            {8, 9}
        };

        for (int[] row : triangle) {
            for (int eles : row) {
                System.out.printf("%4d", eles);
            }
            System.out.println();
        }
        /*调试输出*/
        System.out.println(Arrays.deepToString(triangle));

    }

    public static void wrapperClass() {
        /* 泛型类不能使用基本类型作为类型参数, 包装类内部值比较一定要用equals方法*/
    /*    Integer  - int
        Byte - byte
        Short - short
        Long - long
        Character - char
        Float  -  float
        Double - double
        Boolean - boolean*/



    }

    public static void testInt() {
        /*1，无论如何，Integer与new Integer不会相等。不会经历拆箱过程，new出来的对象存放在堆，而非new的Integer常量则在常量池（在方法区），他们的内存地址不一样，所以为false。

2，两个都是非new出来的Integer，如果数在-128到127之间，则是true,否则为false。因为java在编译Integer i2 = 128的时候,被翻译成：Integer i2 = Integer.valueOf(128);而valueOf()函数会对-128到127之间的数进行缓存。

3，两个都是new出来的,都为false。还是内存地址不一样。

4，int和Integer(无论new否)比，都为true，因为会把Integer自动拆箱为int再去比。
*/

        int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);
        System.out.println(i == i2); //Integer会自动拆箱为int，所以为true
        System.out.println(i == i3); //true，理由同上
        Integer i4 = 127;//编译时被翻译成：Integer i4 = Integer.valueOf(127);
        Integer i5 = 127;
        System.out.println(i4 == i5);//true
        Integer i6 = 128;
        Integer i7 = 128;
        System.out.println(i6 == i7);//false
        Integer i8 = new Integer(127);
        System.out.println(i5 == i8); //false
        Integer i9 = new Integer(128);
        Integer i10 = new Integer(123);
        System.out.println(i9 == i10);  //false
    }
    /**
     * Object 类型:equals, hashcode
     */

    /**
     * String 类型的特点
     */
}
