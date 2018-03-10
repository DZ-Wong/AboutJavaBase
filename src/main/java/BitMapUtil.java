/**
 * 交易位图设置。
 * Created by vip on 2018/2/1.
 */
public class BitMapUtil {
    /**
     * 检查位图num的第i位是否是1。
     * @param i 交易对应位图的位置。
     * @param num 交易开关位图。
     * @return true 支持， false 不支持。
     */
    public static boolean CheckTxnSwitch(int i, int num) {

        return ((num & (1 << i)) != 0);

    }

    /**
     * 将 位图 num 的第 i 位的值 置为 1。
     * @param num 位图
     * @param i 需要设置的位置
     * @return 设置后的位图
     */
    public static int SetBitTrue(int num, int i) {
        return (num | (1 << i));
    }

    /**
     * 将 位图 num 的第 i 位的值 置为 0。
     * @param num 位图
     * @param i 需要设置的位置
     * @return 设置后的位图
     */
    public static int SetBitFalse(int num, int i) {
        int mask = ~(1 << i);//000100
        return (num & (mask));//111011
    }
}
