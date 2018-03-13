package T0308.Impl;

import java.util.ArrayList;

/**
 * Created by vip on 2018/3/13.
 */
public interface Const<T> {
    //定义在接口中的变量自动变为 public static final
    //接口中，无法拥有实例变量。接口指定行为，而不是状态
    int NORTH = 1;
    int NORTH_EAST =2;

    //静态方法，调用者就不用关心它是哪个类
    public static void digitsOf(int n) {
        //
    }

    //默认方法   给定默认实现，default 修饰； 有利于接口演化，接口新增不会影响继承者编译
    default boolean hasNext() {
        return true;
    }


    //如果没有任何接口为共享方法提供默认实现，那么就没有冲突，如果继承的多个接口都有同名的方法且有默认实现，就会有冲突
    //实现类有两种选择： 实现方法； 或不实现方法并将该类声明为抽象类， abstract


    //如果一个类继承了一个父类且实现了一个接口， 而且从父类和接口这两者继承了同样的方法，
    //这时，只关心父类的方法，直接忽视来自接口的默认方法；


    //方法能访问自己所在类对象的任何私有特性







}
