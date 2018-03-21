package T0308.AccessControl;

/**
 * Created by vip on 2018/3/8.
 */
public class AccessControl {
    /**
     * public protected default private
     * 对于class method field的修饰作用
     */


    //default 包访问权限


    //protected 继承访问权限。就类用户而言，这是private的，但对于任何继承于此类的导出类或其他
//    任何位于同一包内的类来说，它是可以访问的


}

class Cookie {
    public Cookie() {
        System.out.println("Cookie Constructor");
    }

    void bite() { //包访问权限，其他包，即使是子类也不能访问它
        System.out.println("bite");
    }

    //为了只允许子类访问权限，用protected
    protected void sing() {
        System.out.println("sing");
    }
}