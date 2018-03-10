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
     * final
     */

    /**
     * finally
     */

    /**
     * finalize
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
    public static void main(String[] args) {
        Base.stringOp();
        Base.format_eg();
    }
}
