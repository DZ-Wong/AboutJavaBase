package forExam;

import javax.print.DocFlavor;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 华为机试准备。
 */
public class Chapter1 {
    /**
     * 1.输入流
     * cin.next();//以空格为分割符
     * cin.nextLine();//以回车结束
     * ((,)|(\\s+))
     */
    public static void inputDemo() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            String s = cin.nextLine();
            //"((,)|(\\s+))"
            //[ ,.]
            String c[]  = s.split("((,)|(\\s+))");
            System.out.println(Arrays.toString(c));
            System.out.println();
        }
    }

    /**
     * 字符串与整形之间互换。
     */
    public static void conversion() {
        //字符串 --> 整形
        int iB = Integer.valueOf("234");
        //整形  --> 字符串
        String sB = Integer.toString(iB);
        //翻转
        StringBuilder c = new StringBuilder(sB);
        c = c.reverse();
        int iD = Integer.valueOf(c.toString());

        System.out.println(iD);
    }

    /**
     * 字节串 byte[]与 字符串 String
     */
    public static void string2bytes()  {
        String origin = "Hello你好";
        byte[] bytes = new byte[0];
        try {
            bytes = origin.getBytes("UTF-8");
            System.out.println(Arrays.toString(bytes));
            String str = new String(bytes, "UTF-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * "12345"  -->  {1, 2, 3, 4, 5}
     */
    public static void string2SingleBytes() {
        String str = "12345";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));//49, 50, ...
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] -= (byte) '0';
        }
        System.out.println(Arrays.toString(bytes));
    }

    /**
     *  "123456"  --> 0x12, 0x34, 0x56
     */
    public static void string2HexBytes() {
        String str = "123456";
        byte[] ret = new byte[str.length()/2];
        byte[] tmp = str.getBytes();

        for (int i = 0; i < tmp.length/2; i++) {
            byte b0 = Byte.decode("0x" +
                    new String(new byte[]{tmp[i*2]})).byteValue();
            System.out.println("b0=" + b0);
            b0 = (byte) (b0 << 4);
            System.out.println("b10=" + b0);
            byte b1 = Byte.decode("0x" +
                    new String(new byte[]{tmp[i*2+1]})).byteValue();
            System.out.println("b1=" + b1);
            ret[i] = (byte) (b0 ^ b1);
            System.out.println("ret=" + ret[i]);

        }
        System.out.println(Arrays.toString(ret));
        System.out.println(Integer.parseInt("34", 16));
    }


    public static void main(String[] args) {
        inputDemo();
//        conversion();
//        string2bytes();
//        string2SingleBytes();
//        string2HexBytes();
    }

}
