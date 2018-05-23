package Interview;

public class HuaWei {

    public static void changeStr(String str) {
        str = "welcome";
    }

    static boolean foo (char c) {
        System.out.print(c);
        return true;
    }

    public void test() {
        String str = "1234";
        changeStr(str);
        System.out.println(str);

        int i = 0;
        for (foo('A'); foo('B') && (i < 2); foo('C')) {
            i++;
            foo('D');
        }
    }

    public static void main(String[] args) {
        HuaWei huaWei = new HuaWei();
        huaWei.test();
    }

}
