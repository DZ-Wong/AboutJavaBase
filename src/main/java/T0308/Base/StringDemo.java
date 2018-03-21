package T0308.Base;

import java.util.Formatter;

/**
 * Created by vip on 2018/3/21.
 */
public class StringDemo {
    /*String类型 不可变
    * 用于String的 ‘+’ 和 ‘+=’ 是java中仅有的两个重载过的操作符，java不允许程序员重载任何操作符
    *
    * 考虑到效率，编译器会对String的多次+操作进行优化，优化使用StringBuilder操作（
    * javap -c class字节码文件名 命令可以查看具体优化过程）
    * */


    /* 如果已经知道最终的字符串有多长，预先指定StringBuilder的大学可以避免多次重新分配缓冲*/

    public static void main(String[] args) {
        System.out.printf("%d", 1);
        System.out.format("%d", 1);

        Formatter f = new Formatter(System.out);
        f.format("%d", 1);

    }
}


class WitherStringBuilder {
    public String implicit(String[] fields) {
        String result = "";
        for (int i = 0; i < fields.length; i++) {
            result += fields[i];
        }
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <fields.length; i++) {
            result.append(fields[i]);
        }
        return result.toString();
    }

}

//javap -c WitherStringBuilder