package utils;

/**
 * Created by vip on 2018/2/23.
 */
public class String2Other {

    public void Other2String() {
        long aa = 234;
        String a = String.valueOf(aa);
        //2
        String b = aa + "";

        System.out.println("a:" + a + "\nb:" + b);
    }

    public void String2Other(){
        String a = "123";

        long aa = Long.valueOf(a);
        int bb = Integer.valueOf(a);

        System.out.println( aa + "\n" + bb);
    }

    public static final void main(String[] args) {
        String2Other ss = new String2Other();

        ss.Other2String();

        ss.String2Other();

        TimeUtil.getTime();
    }
}
