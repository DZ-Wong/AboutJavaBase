package T0308.Base;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by vip on 2018/3/8.
 */
public class Base {
    /**
     * equals
     */

    /**
     * hashcode
     */

    /**
     * string/ stringbuffer
     */



    /**
     * Java 8 版本特性
     *      日期API
     *      Lambda；
     *      函数式接口；
     */

    public static void base() {
        System.out.println(Math.pow(2, 7));
        System.out.println(Math.min(1, 2));
        System.out.println(Math.max(2, 3));
        System.out.println("PI is " + Math.PI + "; E is " + Math.E);
        double x = 3.75;
        int n = (int) x;
        int m = (int) Math.round(x);//四舍五入
        System.out.println(n);
        System.out.println(m);
        long la = 2341234123L;
        int ia = Math.toIntExact(la);//无法转换时会报异常


//        && 、 || 短路逻辑判断
//        & 、 |  非短路逻辑判断
    }

    public static void BigNum() {
        BigInteger n = BigInteger.valueOf(99678998989787887L);
        BigInteger k = new BigInteger("12389741823791824");
        //java 不允许对象使用操作符，因此操作大数时，必须调用方法
        n.add(k);
        BigInteger nk = BigInteger.valueOf(5).multiply(n.add(k));

        BigDecimal.valueOf(2, 0).subtract(BigDecimal.valueOf(11, 1));


    }

    public static void stringOp() {
        int age = 10;
        System.out.println("next year, you will be " + age + 1);
        System.out.println("next year, you will be " + (age + 1));
        String names = String.join(",", "wdz", "dzw", "zwd");


//        StringBuilder builder = new StringBuilder();
//
//        while (more strings) {
//            builder.append(next string);
//        }
//
//        String result = builder.toString();

        String greeting = "Hello,   World!";
        String location = greeting.substring(9, 14);

        String[] result = greeting.split(",");
        String[] result2 = greeting.split("\\s+");

        String world = "World";
        String world2 = new String("World");
        System.out.println(world == world2 );
        System.out.println(location);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(location == world);
        if ("World".equals(location)) { /* 文字放前面*/}
        if ("world".equalsIgnoreCase(location)) {/* 忽略大小写*/}

        String first = "first";
        String second = "second";
        int n = first.compareTo(second);
        if (n > 0) {
            /*first 排在second 后面 */
        }

//        Collator

        String message = String.format("%s , %d", "abc", 2 );

        StringBuffer s1 = new StringBuffer(10);
        s1.append("1234");
        System.out.println(s1.length() + "; capacity() = " + s1.capacity());
    }

    public static void format_eg() {
        System.out.printf(
            "%+d \n % d \n %-d \n %0d \n %(d \n %,d \n %#f \n %#x",
            2 ,
            3 ,
            4 ,
            5 ,
            -6 ,
            123412341 ,
            3333 ,
            1001


        );
    }

    public static void equalsAndCompareto() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a == b);
        System.out.println(a.equals(b));// true
        System.out.println(a.compareTo(b));

        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));// true
        System.out.println(s1.compareTo(s2));
        /**
         * 当引用类型用（==）进行比较的时候
         　　比较的是他们在内存中的存放地址，除非地址一样，否则比较后结果为false。

         基本数据类型比较只能用==,不能用equals,因为用equals(参数),参数必须为对象。
         equals的方法在在Object中定义，这个方法的初始行为是比较对象的内存地址
         （如上面的例子），因为Object的equals方法也是用双等号（==）进行比较的
         ，所以比较后的结果跟用双等号（==）的结果相同。


         但是一些类中这个方法被重写了，如String,Integer,Date重写后的equals
         不再是比较对象在内存的存放地址了，而是比较的内容。

         通常compareTo用来比较大小的 而equals是比较是否相等的。

         　　　　同比之下，equals的效率高，因为compareTo是按字典顺序比较两个字符串。也就是按顺序比较字符串中各个字符的 Unicode值。

         　　　compareTo时：

         　　　　当相同时：返回int 0；当小于时，如果此位为空，如果少一位，则返回-1，否则返回Unicode差值，
         当大于时，如果参数对应位为空，如果多一位，则为1，否则为差值。
         */
    }
    public static void main(String[] args) {
//        Base.stringOp();
//        Base.format_eg();
//        Base.equalsAndCompareto();

        int i = 12;
//        int a = i += i-= i*=i;
        System.out.println(i += i-= i*=i);

        Object obj = new Object();
//        obj.
    }
}
