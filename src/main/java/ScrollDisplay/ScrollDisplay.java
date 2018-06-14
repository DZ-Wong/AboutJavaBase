package ScrollDisplay;

/**
 * LED 滚动显示---单色。
 */
public class ScrollDisplay {

    /**
     * 每个LED大小，可调整。
     */

    public final static int ROW = 16;
    public final static int COL = 16;

    /**
     * 每个LED间隔
     */
    private final static int SEPARATOR = 1;
    private final static char FILL_CHAR = '#';
    private final static char SPACE_CHAR = ' ';


    /**
     * 输出数组到LED。
     * @param chs
     */
    public static void print(char[][] chs) {
        for (int i = 0; i < chs.length; i++) {
            for (int j = 0; j < chs[i].length; j++) {
                System.out.println(chs[i][j]);
            }
            System.out.println();
        }
    }

//    public char[][] getLED(String num) {
//
//    }


    public static void main(String[] args) {
        try {
            CharCode.getCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
