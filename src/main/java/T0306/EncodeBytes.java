package T0306;

import java.io.UnsupportedEncodingException;

/**
 * 编码转换。
 * Created by vip on 2018/3/6.
 */
public class EncodeBytes {

    public static void getBytes () {
        String str = "你好";
        byte [] bytes = null;
        try {
            bytes = new String("我好").getBytes("gbk");
            System.out.println(new String(bytes, "gbk"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte [] src, dst;
        try {
            src =  new String("我好").getBytes("gbk");

            dst=new String(src,"GBK").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
