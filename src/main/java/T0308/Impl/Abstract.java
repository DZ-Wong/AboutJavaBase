package T0308.Impl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vip on 2018/3/13.
 */
public class Abstract {
    //lambda 表达式 -- 期望只有一个抽象方法的接口对象时可用。
    //只能 将其放入类型为   函数式接口   的变量中， 这样它就被转换为该接口的实例。
/*
    (String first, String second)  -> first.length() - second.length();
    一个参数时可省略();
    参数类型可推导时可以不声明；

*/
    public static void lambda() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("efg");
        list.removeIf(e -> e == null);//
//        list.removeIf(Object::isNull);

        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();

        // Java 8之前：
        JButton show =  new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

        // Java 8方式：
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });


//        // Java 8之前：
//        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        for (String feature : features) {
//            System.out.println(feature);
//        }


        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }



    /*局部类 -- 当一个类实现了一个接口并且方法的调用者只关心接口，而不是类时*/
    /*两个好处：1.类名称隐藏在方法范围内；2.局部类的方法可以访问来自闭合作用域的变量，就像lambda表达式的变量*/
    


}
