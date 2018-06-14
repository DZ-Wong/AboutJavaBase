package Breakthrough;

/**
 * 数组---初始化
 */
public class ArraysDemo {
    public static void initMode() {
        //静态初始化1
        String[] books = new String[] {
                "VOA",
                "ABC",
                "Hujiang"
        };
        //静态初始化2
        String [] names = {
                "word",
                "pronounce",
                "grammar"
        };
        //动态初始化
        String [] strArr = new String[5];

        /*栈存数组局部变量， new出来的数组对象存在堆
        * 默认值种类包括：
        * 0、 0.0、\u0000、false、null*/
        System.out.println("length of books is " + books.length);
        System.out.println("length of names is " + names.length);
        System.out.println("length of strArr is " + strArr.length);
    }

    public static void main(String[] args) {
        initMode();
    }
}
