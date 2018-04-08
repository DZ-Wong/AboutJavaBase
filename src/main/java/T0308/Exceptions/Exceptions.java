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
     * 处理应该统一，避免各处散落很多异常处理逻辑；
     * 异常可控-相应要有应对方法（运维）；
     *
     */
/**
 * 1.子类在覆盖父类方法时，父类的方法如果抛出了异常，那么子类的方法只能抛出父类的异常或者该异常的子类。

 2.如果父类抛出多个异常，那么子类只能抛出父类异常的子集。----子类覆盖父类只能抛出父类异常或者子类或者子集。如果父类的方法没有抛出异常，那么子类覆盖时绝对不能抛，只能try。
 */
    /**
     * 体系：
     * Exception体系分两种：
     1.一种是编译时被检测异常（throws）。除runtimeException子类的所有子类。这样的问题可以针对性的处理。

     2.运行时异常（throw）。Exception的子类中runtimeException和其子类。这种问题一般不处理，直接编译通过，在运行时让调用时的程序强制停止。
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
