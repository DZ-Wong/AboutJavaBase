package T0308.OOP;

/**
 * Created by vip on 2018/3/21.
 */
public class Composition {
    /*组合
    * 一、相比于组合，继承有以下优点：

1、在继承中，子类自动继承父类的非私有成员(default类型视是否同包而定)，在需要时，可选择直接使用或重写。

2、在继承中，创建子类对象时，无需创建父类对象，因为系统会自动完成；而在组合中，创建组合类的对象时，通常需要创建其所使用的所有类的对象。

二、组合的优点：

1、在组合中，组合类与调用类之间低耦合；而在继承中子类与父类高耦合。

2、可动态组合。
    * */

    public void composition() {
        new Draw().draw();
        new Erase().erase();
    }

    public static void main(String[] args) {
        new Composition().composition();
    }
}


class Draw {
    public void draw() {
        System.out.println("draw");
    }
}

class Erase {
    public void erase() {
        System.out.println("erase");
    }
}

