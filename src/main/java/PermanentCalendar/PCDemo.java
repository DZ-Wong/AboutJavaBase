package PermanentCalendar;


import java.util.Scanner;

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
        System.out.printf("%32s\n", year + " 年  " + month + " 月");
        System.out.println("----------------------------------------------------------------");
        String[] title = {"日", "一", "二", "三", "四", "五", "六"};
        for (int i = 0; i < title.length; i++) {
         System.out.printf("%-12s", title[i]);
        }
        System.out.println();
    }

    /**
     * 判断闰年。
     */
    public static boolean isLeapYear(int year) {
        return  ((year%4 == 0 && year%100 != 0)||(year%400 == 0));
    }
    /**
     * 计算截止今年的总天数。
     * @param year
     * @return
     */
    public static int daysTillYear(int year) {
        int sum = 0;
        for (int i = 1970; i < year; i++) {
            if (isLeapYear(i)) {
                sum += 366;
            } else {
                sum += 365;
            }
        }
        return sum;
    }

    /**
     * 每个月的天数。
     */
    public static int daysOfEachMonth(int year, int month) {
        int sum = 0;
        switch (month) {
            case 2:
                if (isLeapYear(year)) {
                    sum += 29;
                } else {
                    sum += 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                sum =30;
                break;
            default:
                sum = 31;
                break;
        }
        return sum;
    }

    /**
     * 计算本月一号星期几。
     */
    public static int weekNum(int year, int month) {
        int totalDays = 0;
        totalDays = daysTillYear(year);
        for (int i = 1; i < month; i++) {
            totalDays += daysOfEachMonth(year, i);
        }

        int temp = (4+ totalDays%7) % 7;
        if (7 == temp) {
            return 0;
        }
        return temp;
    }

    /**
     * 打印日历。
     */
    public static void printAll(int daysOfMonth, int weekOfFirstDay) {
        for (int i = 0; i < weekOfFirstDay; i++) {
            System.out.printf("%-13s", " ");
        }
        for (int i = weekOfFirstDay+1, j =1; i < daysOfMonth + weekOfFirstDay + 1; i++, j++) {

            if (i%7 == 0) {
                System.out.printf("%-13d\n", j);
            } else {
                System.out.printf("%-13d", j);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String isRun = "y";
        while (!isRun.equals("exit")) {
            System.out.println("input year");
            int year = in.nextInt();
            System.out.println("input month");
            int month = in.nextInt();
            printTitle(year, month);
            printAll(daysOfEachMonth(year, month), weekNum(year, month));
            System.out.println("continue ?");
            isRun = in.next();
        }
    }
}
