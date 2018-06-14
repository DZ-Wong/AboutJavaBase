package ScrollDisplay;

/**
 * 汉字转点阵显示。
 */
public class CharCode {
    public static void getCode() throws Exception{
        String str = "我爱你";

        byte[] bStr = str.getBytes("GB2312");

        for (int i = 0; i < bStr.length; i++) {
            System.out.println(Integer.toHexString(bStr[i]));
        }

    }
}
