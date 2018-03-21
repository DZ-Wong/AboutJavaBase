package T0308.Impl;

public class ImplandLambda {
    /* 函数式变成非常适合并发和事件驱动（“反应式”）编程*/
    /*
    接口指定了一组实现类必须提供的方法；
    接口是任何实现该接口的父类。so， 可以将类的实例赋值给接口类型的变量；
    接口可以包含静态方法。接口的所有变量默认是static 和final的；
    接口可以包含默认方法，实现类可以继承或者覆盖该默认方法。
    Comparable 和 Comparator 接口是用来比较对象的
    lambda 表达式代表一段代码块，这块代码可以在稍后的某个时间点执行。类可以嵌套在其他类中。
    lambda 表达式转变为函数式接口。
    方法引用和构造函数引用在不调用它们的情况下引用方法或构造函数。
    lambda 表达式 和局部内部类可以有效访问来自闭合作用域的final变量
     */

    public static double average(IntSequence seq, int n) {
        int count = 0;
        double sum = 0;
        while (seq.hasNext() && count < n) {
            count++;
            sum += seq.next();
        }
        return count == 0 ? 0 : sum / count;
    }
    public void show() {
        //转换为接口类型  子类型T的任何值不需要转换就能赋值给父类型
        //向上转型无条件。接口是接口实现的父类。
        IntSequence digits = new DigitSequence(1729);
        double avg = average(digits,1729);




    }

}
