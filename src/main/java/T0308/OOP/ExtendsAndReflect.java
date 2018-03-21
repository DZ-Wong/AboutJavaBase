package T0308.OOP;

/**
 * 继承和反射。
 * Created by vip on 2018/3/13.
 */
public abstract class ExtendsAndReflect {
    public int i;
    public static int y;
    /*实例变量和静态变量统称为域
    * 类中的域、方法和嵌套类、接口统称为类成员*/

    /*不同于接口，抽象类可以拥有实例变量和构造函数*/
    public abstract int person();

    /*受保护访问*/
    //将父类方法或者变量声明为protected  -- 作用是什么？




    /*public class Worker {
        public void work() {
            for (int i = 0; i < 100; i++ ) System.out.println("Working");
        }

        public String toString() {
            return getClass.getName() + "...";

        }

        public class ConcurrentWorker extends Worker {

            public void work() {
                Thread t = new Thread(super::work);
                t.start();
            }

            public String toString() {
                return super.toString();

            }
        }

        //当对象与一个字符串连接时，Java编译器自动调用该对象的toString方法。
        // x.toString()    ==     "" + x

        //Object 是所有类的父类，基本的类都有toString方法

        int[] primes = {2, 3, 5, 7, 11, 13};
Arrays.toString(primes);	//数组的输出
        int[][] princess = {
            {2, 3, 5, 6, 7},
            {4, 5, 6, 9},
            {9, 8, 7}
        };
Arrays.deepToString(princess);//多维数组的输出

        public class Item {
            private String description;
            private double price;

            public boolean equals(Object otherObject) {
                //快速检测对象是否相等
                if ( this == otherObject ) return true;

                //如果参数为null， 则必须返回false
                if (otherObject == null) return false;

                //检查otherObject是否是Item类型
                if (getClass() != otherObject.getClass()) return false;

                //检查实例变量值是否相同
                Item other  = (Item) otherObject;
                return Objects.equals(description, other.description) && price == other.price;

            }
        }*/


        //判断是否用继承
    /*是否需要从新类向基类进行向上转型，如果必须则用继承，否则考虑用组合。
    * 只有子类真正是超类的子类型时，才适合用继承。 is-a 关系*/

    /*多用组合， 少用继承。
    * 组合是 has-a 关系*/




}
