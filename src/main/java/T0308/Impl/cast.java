package T0308.Impl;

/**
 * Created by vip on 2018/3/13.
 */
public class cast {
    /*从父类转换为子类时，需要强制转换cast
    * 为了避免异常，使用instanceof测试是否是预期类型*/

//    Object x instanceof type, 如 type 是 Object 的父类，返回true
    //x 是不是 type的子类
    //getclass  严格判断是不是该类，不存在继承关系
    public static void testInstanceof(Object x) {
        System.out.println("x instanceof Parent : " + (x instanceof Parent));
        System.out.println("x instanceof Child : " + (x instanceof Child));
        System.out.println("x getClass Parent : " + (x.getClass() == Parent.class));
        System.out.println("x getClass Child : "  + (x.getClass() == Child.class));

    }


    //单继承  多实现
    //接口继承接口


    public static void main(String[] args) {
        testInstanceof(new Parent());
        System.out.println("-------------------");
        testInstanceof(new Child());
    }
}
