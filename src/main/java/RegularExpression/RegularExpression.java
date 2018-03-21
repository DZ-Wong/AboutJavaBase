package RegularExpression;

/**
 * 正则表达式示例。
 * Created by vip on 2018/3/21.
 */
public class RegularExpression {
    public static boolean demo(String regex, String str) {
        return false;
    }

    public static void main(String[] args) {
            String regex = "[1-9][0-9]{4,14}";/*
            []表示取值范围，{}表示次数，()表示组
            */
            String demo = "89646213";
            boolean b = demo.matches(regex);
            System.out.println(demo + " : " + b);
    }
}
