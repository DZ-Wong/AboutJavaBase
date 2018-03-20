package T0308.OOP;

/**
 * Created by vip on 2018/3/20.
 */
public class Polymorphic {
    /*除了static 和final （private 本质上属于final方法，因为不能被子类访问）方法之外，
    * 所有的方法都是动态绑定。*/
    /*final 方法会使编译器生成更有效的代码*/
    /*如果某个方法是静态的，其行为就不具有多态性,不能重写*/

    public static void main(String[] args) {
        StaticSuper sup = new StaticSub();
        System.out.println(sup.dynamicGet());
        System.out.println(sup.staticGet() + "\n\n");//

        new RoundGlyph(5);
        /*构造函数调用顺序
        * 1.将分配给对象的存储空间初始化成二进制0；
        * 2.调用基类构造函数，从根开始递归下去，因为多态性此时调用子类覆盖后的draw方法，由于1，radius值为0
        * 3.按照声明顺序调用成员的初始化方法
        * 4.最后调用子类的构造函数
        * */


        /*非private方法才可以被覆盖，子类中覆盖private方法是一个新方法，而不是重写*/
        /*属性域的访问操作有编译器解析，因此不是多态的。父类和子类同名属性都会分配不同的存储空间*/

        Super sup1 = new Sub();
        System.out.println("sup.filed = " + sup1.field +
                           ", sup.getField() = " + sup1.getField());
        Sub sub = new Sub();
        System.out.println("sub.filed = " + sub.field +
                           ", sub.getField() = " + sub.getField() +
                           ", sub.getSuperField() = " + sub.getSuperField());



    }

    /*构造函数实际上是static方法，不具有多态性，不能被重写*/
    /*在父类构造函数内部调用具有多态行为的函数将导致无法预测的结果，因此时子类对象还没初始化*/




}

class StaticSuper {
    public static String staticGet() {
        return "Base staticGet()";
    }

    public String dynamicGet() {
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper {
    public static String staticGet() {
        return "Derived staticGet()";
    }

    public String dynamicGet() {
        return "Derived dynamicGet()";
    }
}

class Glyph {
    void draw() {
        System.out.println("Glyph.drwa()");
    }
    Glyph() {//2
        System.out.println("Glyph() before draw()");
        draw();//
        System.out.println("Glyph() after draw()");//4
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    RoundGlyph(int r) {//1
        radius = r;//5
        System.out.println("RoundGlyph.RoundGlyph().radius = " + radius);
    }
    void draw() {//3
        System.out.println("RoundGlyph.draw().radius = " + radius);
    }
}

class Super {
    public int field = 0;

    public int getField() {
        return field;
    }
}

class Sub extends Super {
    public int field = 1;

    @Override
    public int getField() {
        return field;
    }

    public int getSuperField() {
        return super.field;
    }
}

