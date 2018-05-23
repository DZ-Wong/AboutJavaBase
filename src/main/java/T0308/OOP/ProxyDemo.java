package T0308.OOP;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by vip on 2018/3/21.
 */
public class ProxyDemo {

    /*将额外操作从实际对象中分离到不同的地方，能快速插入和删除这些操作*/

    /**
     *
     AOP，是通过动态代理实现的。

     一、简单来说：

     　　JDK动态代理只能对实现了接口的类生成代理，而不能针对类

     　　CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法（继承）

     二、Spring在选择用JDK还是CGLiB的依据：

     (1)当Bean实现接口时，Spring就会用JDK的动态代理

     (2)当Bean没有实现接口时，Spring使用CGlib是实现

     　  (3)可以强制使用CGlib（在spring配置中加入<aop:aspectj-autoproxy proxy-target-class="true"/>）

     三、CGlib比JDK快？

     　 (1)使用CGLib实现动态代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。

     　 (2)在对JDK动态代理与CGlib动态代理的代码实验中看，1W次执行下，JDK7及8的动态代理性能比CGlib要好20%左右。
     * @param iface
     */

    //静态代理
    //执行代理对象的方法
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());//真正对象的执行。
        consumer(new SimpleProxy(new RealObject()));//代理对象的生成和执行

        //另一个例子
        //目标对象
        UserDao target = new UserDao();

        //代理对象，把目标对象给代理对象，建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);

        proxy.save();//执行代理方法


        //动态代理
        IUserDao target2 = new UserDao();
        System.out.println(target2.getClass());

                    //代理对象，把目标对象给代理对象，建立代理关系
        IUserDao proxy2 = (IUserDao) new ProxyFactory(target2).getProxyInstance();
        System.out.println(proxy2.getClass());
        proxy2.save();
        //代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理

        //Cglib代理
        UserDaoCgilb target3 = new UserDaoCgilb();

        //代理对象
        UserDaoCgilb proxy3 = (UserDaoCgilb) new ProxyFactoryCglib(target3).getProxyInstance();

        //执行代理对象方法
        proxy3.save();
        //在Spring的AOP编程中:
//        如果加入容器的目标对象有实现接口,用JDK代理
//        如果目标对象没有实现接口,用Cglib代理

        //创建代理对象
        Interface proxy4 = (Interface)Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[] {Interface.class},
                new DynamicProxHandler(new RealObject()));
        //执行代理对象方法
        consumer(proxy4);

    }
}

interface Interface {//定义对象的行为
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {//目标业务对象类
    @Override
    public void doSomething() {
        System.out.println("dosomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}

//静态代理
class SimpleProxy implements Interface {//代理类
    private Interface proxy;

    public SimpleProxy(Interface proxy) {//记住要代理的目标对象
        this.proxy = proxy;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy dosomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse " + arg);
    }
}


//动态代理,JDK代理，要求目标对象实现了一个接口
//接口
interface IUserDao {
    void save();
}

//目标对象
class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----------saved -------------");
    }
}

//代理对象
class UserDaoProxy implements IUserDao {

    //接收保存目标对象
    private IUserDao target;
    public  UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务----");
        target.save();
        System.out.println("提交事务----");
    }
}


//代理工厂类
class ProxyFactory {
    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new InvocationHandler() {//这个Handler对象可以外置，实现多个方法的代理。
                  @Override
                  public Object invoke(
                      Object proxy, Method method, Object[] args)
                      throws Throwable {
                      System.out.println("开始事务2--------");
                      Object returnValue = method.invoke(target, args);
                      System.out.println("提交事务2--------");
                      return returnValue;
                  }
              });
    }
}

//Cglib代理，子类代理，代理没有实现接口的类。 如拦截器。 底层原理是字节码处理框架ASM转换字节码生成新的类。
//在内存中动态构建子类。代理的类不能是final类。 目标对象的方法如果为final/static，就不会被拦截，。

//目标对象类
class UserDaoCgilb {
    public void save() {
        System.out.println("---------save----------");
    }
}

//Cglib代理工厂,对UserDarCglib 在内存中动态构建一个子类对象
class ProxyFactoryCglib implements MethodInterceptor {

    //维护目标对象
    private Object target;

    public ProxyFactoryCglib(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance() {
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类（代理对象）
        return en.create();
    }
    @Override
    public Object intercept(
        Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("Cgilb代理 ----");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);

        System.out.println("Cglib代理提交事务 ----");
        return returnValue;
    }
}

//多个方法时的写法

class DynamicProxHandler implements InvocationHandler {
    //代理目标对象
    private Object proxy;

    public DynamicProxHandler(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*** proxy: " + proxy.getClass() +
                            ". method: " + method + ". args: " + args);

        if (args != null) {
            for (Object arg : args) {
                System.out.println(" " + arg);
            }
        }
        return method.invoke(this.proxy, args);
    }
}


