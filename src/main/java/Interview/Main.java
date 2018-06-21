package Interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private LinkedList list = new LinkedList();
    private Map<Character, Integer> map = new HashMap();
    /**
     * 将字符串存进链表。
     * @param str
     * @return
     */
    public  void str2List(String str) {
        char a[] = str.toCharArray();
        for (int  j = 0; j * 5 < str.length(); j++) {
            this.list.add(j, a[j*5]);
        }
    }

    public  void norstr2List(String str) {
        char a[] = str.toCharArray();
        for (int  j = 0; j< str.length(); j++) {
            list.add(j, a[j]);
        }

    }
    /**
     * 打印链表。
     * @param list
     */
    public  void printList(LinkedList list) {
        int i;
        for (i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + "->");
        }
        System.out.println(list.get(i));
    }

    /**
     * 找首节点。

     * @return
     */
    public  char findOne() {
        for (int i = 0; i < this.list.size(); i++) {
            if (!this.map.containsKey(this.list.get(i))) {
                this.map.put((Character) this.list.get(i), 1);
            } else {
                return (char )this.list.get(i);
            }
        }
        return '0';
    }

    public  String filterStr(String str) {
        String[] tmp = str.split("[ ->]");
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            s.append(tmp[i]);
        }
        return s.toString();
    }

    public void clear() {
        this.map.clear();
        this.list.clear();
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            StringBuilder sb = new StringBuilder();
            sb.append(in.nextLine());

            m.norstr2List(m.filterStr(sb.toString()));

            char a = m.findOne();
            System.out.println(a);
            m.clear();
        }

//        A -> B -> C -> E -> F ->C
    }
}
