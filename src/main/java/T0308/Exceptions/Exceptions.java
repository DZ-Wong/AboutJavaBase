package T0308.Exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by vip on 2018/3/8.
 */
public class Exceptions {
    /**
     * 处理应该统一，避免各处散落很多异常处理逻辑；异常可控-相应要有应对方法（运维）；
     *
     */

    /**
     * 体系：
     * Throwable；
     * Exception；
     * RuntimeException；
     * Error。
     */

    /**
     * RuntimeException and Exception  区别。
     */

    //覆盖一个方法时，不能抛出比父类方法声明中还要多的已检查异常。
    //如果父类方法没有throws语句，则覆盖后的犯法不能抛出已检查异常。

    public static void tryWithRes() throws FileNotFoundException, IOException {
        try (Scanner in = new Scanner(Paths.get("D:\\test\\test.txt"));
            PrintWriter out = new PrintWriter("out.txt")){

        }//这种形式保证资源对象一定会被关闭  , 这种情况要求资源实现了AutoCloseable接口

        //没有实现AutoCloseable接口的资源
        try {
            //do thing
        } finally {
                //clean up
        }

        //重抛
//        try {
//                //
//
//        } catch (SQLException ex ) {
//              throw new XXXException("more detials", ex);
//
//        }
//        Throwable cause = ex.getCause();

        //未捕获异常会终止其所在的线程

        //存储异常的堆栈踪迹
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ex.printStackTrace(out);
//        String description = out.toString();


    }




    private String direction;
    public void process(String directions) {
        this.direction = Objects.requireNonNull(directions);//检测非空的方法

    }

}
