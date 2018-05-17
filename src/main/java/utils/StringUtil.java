package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuqibin on 2017/12/30.
 */
public class StringUtil {
    private static int increadNum = 1;

    /**
     * @param str
     * @return
     */
    public static Map<String, String> strWithSeparator2Map(String str, String separator) {
        Map<String, String> map = new HashMap();

        String[] strArray = str.split(separator);
        for (int i = 0; i < strArray.length; i++) {
            map.put(strArray[i], strArray[i]);
        }

        return map;
    }

    /**
     * 生成五位随机数
     *
     * @return
     */
    public static int getRandom() {
        return (int) ((Math.random() * 9 + 1) * 10000);
    }

    /**
     * 获取两位递增数
     *
     * @return
     */
    public static int getIncreadNum() {
        if (increadNum > 99) {
            increadNum = 1;
        }
        return increadNum++;
    }

    public static double Long2Doulbe( Long num ) {
        String str = String.format("%.2f", num / 100.00);
        return Double.parseDouble(str);
    }
}
