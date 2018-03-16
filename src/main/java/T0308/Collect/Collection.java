package T0308.Collect;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by vip on 2018/3/8.
 */
public class Collection {
    /**
     * 内部实现方式 --- 应用场景
     */

    /**
     * 集合框架体系：基础Collection， Map
     */

    /**
     * 具体集合实现的内容，List， Set， Map 具体实现，内部结构， 特殊的方法，适用场景
     */

    /*List 列表 有序 有位置编号*/

    /*Set 元素不会被插入指定位置， 不允许重复元素; 可以高效检查某个元素是不是它的元素，在顺序无关时有用*/

    /*Queue 会记录插入顺序， 只能在尾端插入，在头部删除
    * Deque 双端队列  有两个尾端，插入和删除都可以在两个尾部进行*/

    /*map存储键和值之间的联系*/

    /*如果想按顺序遍历集合，可使用TreeSet*/

    /*ConcurrentHashMap  不允许键和值 为null*/

    //Entry 是什么

    //Properties  实现了文本格式保存和加载的映射
    //ASCII编码 # ！ 两个为注释开头

    //BitSit 位组 存储一系列比特。 不是集合， 没有实现Collection《》接口

    //枚举Set  和枚举Map

    //栈 stack 是在一端（栈顶）进行添加和删除元素的数据结构
    //队列queue 让你高效地在一端（尾端）添加元素，在另一端（头部）删除元素
    //双端队列deque 支持两端进行插入和删除操作
    //需要使用相关东西且不关系线程安全时，使用ArrayDeque

    /**
     * 工具类Collections等用法
     */
    public static void useCollect() {
        /*Collection<String> coll = ....;
        Iterator<String> iterator = coll.iterator();
        while (iterator.hasNext()) {
            String eles = iterator.next();
            //...
        }*/
        /*for (String eles : coll) {
            //...
        }*/


        Map<String, Integer> counts = new HashMap<>();
        counts.put("Alice", 1);
        counts.put("Alice", 2);//更新键对应的值

        int count = counts.get("Alice");//如果键不存在， 返回null
        int count2 = counts.getOrDefault("Alice", 0);
        //这个函数在键不存在时返回默认的0；

        //如果需要更新操作，需要确认键是否存在，快捷方法是
        counts.merge("Alice", 1, Integer::sum);

    }

    public static void useQueue() {
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("Peter");
        stack.push("Mary");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }


        Queue<String> queue = new ArrayDeque<>();
        queue.add("Paul");
        queue.add("Mao");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
