package Interview;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by vip on 2018/6/19.
 */
public class River {
//    private static  char[] men = new char[100];
    private static  ArrayList list = new ArrayList();

    private static int sum = 0;

    public int count() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        Collections.sort(list);
        System.out.println(list.toString());

        int n = list.size();
        int i = n - 1;
        for (; i > 2; i = i - 2) {


            int countA = (int)list.get(0) + (int)list.get(1) + (int)list.get(1) + (int)list.get(i);
            int countB = (int)list.get(0) + (int)list.get(0) + (int)list.get(i-1) + (int)list.get(i);
            if (countA < countB) {
                sum += countA;
            } else {
                sum += countB;
            }
        }
        if (i == 2) {
            sum = sum + (int)list.get(0) + (int)list.get(1) + (int)list.get(2);
        } else if (i == 1) {
            sum = sum + (int)list.get(i);
        } else {
            sum = sum + (int)list.get(0);
        }
        return sum;
    }

    public static void main(String[] args) {
        River river = new River();
        sum = river.count();
        System.out.println("sum = " + sum);
    }
}
