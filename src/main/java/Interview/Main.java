package Interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * 将字符串存进链表。
     * @param str
     * @return
     */
    public static LinkedList str2List(String str) {
        LinkedList list = new LinkedList();
        char a[] = str.toCharArray();
        for (int  j = 0; j * 5 < str.length(); j++) {
            list.add(j, a[j*5]);
        }
        return list;
    }

    public static LinkedList norstr2List(String str) {
        LinkedList list = new LinkedList();
        char a[] = str.toCharArray();
        for (int  j = 0; j< str.length(); j++) {
            list.add(j, a[j]);
        }
        return list;
    }
    /**
     * 打印链表。
     * @param list
     */
    public static void printList(LinkedList list) {
        int i;
        for (i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + "->");
        }
        System.out.println(list.get(i));
    }

    /**
     * 找首节点。
     * @param list
     * @return
     */
    public static char findOne(LinkedList list) {
        Map<Character, Integer> map = new HashMap();

        for (int i = 0; i < list.size(); i++) {
            list.get(i);
            if (!map.containsKey(list.get(i))) {
                map.put((Character) list.get(i), 1);
            } else {
                return (char )list.get(i);
            }
        }
        return '0';
    }

    public static String filterStr(String str) {
        String[] tmp = str.split("^ ");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            s.append(tmp[i]);
        }
        return s.toString();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String test = in.nextLine();

        String str = filterStr(test);
        System.out.println(str);
        LinkedList list = norstr2List(str);
        System.out.println(list);
        char a = findOne(list);
        System.out.println(a);
//        A -> B -> C -> E -> F ->C
    }
}
