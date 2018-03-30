package PermanentCalendar;

/**
 * 万年历打印。
 * Created by vip on 2018/3/30.
 */
public class PCDemo {
    /**
     * 分析：1.1970年为Unix元年。1970.1.1是星期四。
     * 2.首先，要计算出本月一号是星期几
     *      2.1 计算出年天数，即截止这一年一月一号的天数？
     *      2.2 计算出月天数，即截止本月一号的天数。
     *      2.3 用年天数加月天数，求得本月1号距离1970。1.1 的总天数，判断星期几
     * 3.判断本月的总天数
     * 4. 打印日历。
     */

    /**
    * 打印标题。
     */
    public static void printTitle(int year, int month) {
        System.out.printf("32s\n", year + " 年  " + month + " 月");
        System.out.println("------------------------------------");
        String[] title = {"日", "一", "二", "三", "四", "五", "六"};
        for (int i = 0; i < title.length; i++) {
         System.out.printf("%-12s", title[i]);
        }
        System.out.println();
    }
}
