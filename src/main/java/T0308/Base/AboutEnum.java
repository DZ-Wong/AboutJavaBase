package T0308.Base;

import java.util.Arrays;

/**
 * Created by vip on 2018/3/14.
 */
public class AboutEnum {
    public enum Size { SMALL, MEDIUM, LARGE, EXTRA_LARGE};

    public void aboutEnum() {
        //比较相等 只需要 ==

        //
        Size  mysize = Size.valueOf("SMALL");

        //按照声明顺序输出所有的元素
        Size[] allValues = Size.values();

        System.out.println("mysize is : " + mysize + "; allValues is : " + allValues);

        //遍历枚举
        for (Size s : Size.values()) { System.out.println(s); }


        //枚举实现实例Comparable<E>,, 基于序数值的比较
        //序数值
        Size.EXTRA_LARGE.ordinal();
    }

    public enum Num {
        SMALL("s"), MEDIUM("m"), LARGE("l");
        private String num;

        Num(String num) {//该构造函数 总是私有的；去掉构造函数会报错。
            this.num = num;
        }

        public String getNum() {return  num;}
    }


    //实例的实现体
    public enum Operation {
        ADD {
            public int eval(int arg1, int arg2) { return arg1 + arg2; }
        },
        SUBTRACT {
            public int eval(int arg1, int arg2) { return arg1 - arg2; }
        },
        MULTIPLY {
            public int eval(int arg1, int arg2) { return arg1 * arg2; }
        },
        DIVIDE {
            public int eval(int arg1, int arg2) { return arg1 / arg2; }
        };

        public abstract int eval(int arg1, int arg2);

    }



    //use Operation
    public void User() {
        Operation op = Operation.ADD;//根据输入变化
        int result = op.eval(1, 30);
        System.out.println(result);

        switch (op) {
            case ADD: result = 1 + 2; break;
            case DIVIDE: result = 1 / 2; break;
            case MULTIPLY: result = 3; break;
            case SUBTRACT:  result = 4; break;
        }

        System.out.println(result);
    }

    //枚举可以拥有静态成员，但是由于枚举常量在静态成员之前构建，所有你不能再构造函数里引用任何静态成员
    public enum Modifier {
        PUBLCI, PRIVATE, PROTECTED, STATIC, FINAL, ABSTRACT;

        private int mask;
        //错误示范
        /*private static int maskBit = 1;

        private Modifier() {
            mask = maskBit;//不能再构造函数中访问静态变量
            maskBit *= 2;
        }*/

        //正确方法是在静态初始块中初始化  -- 这段代码有什么用？
        //TODO
        static {
            int maskBit = 1;
            for (Modifier m : Modifier.values()) {
                m.mask = maskBit;
                maskBit *= 2;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(Modifier.values()));
        System.out.println(Modifier.PUBLCI.ordinal());
        for (Modifier s : Modifier.values()) {
            System.out.println(s.ordinal());
        }
    }

}
