import T0306.EncodeBytes;
import T0308.DataType.DataType;
import T0308.JavaIO.JavaIO;

import java.math.BigDecimal;

/**
 * Created by vip on 2018/1/30.
 */
public class Test {

    public static final void main(String[] args){
        Test test = new Test();
        long amt = 123456789012L;
        Double amt3 = 7890.12;
//        System.out.println(StringUtil.Long2Doulbe(amt));
//
//        BigDecimal a = new BigDecimal(Long.valueOf(amt));
//        BigDecimal a2 = new BigDecimal(Double.valueOf(amt3));
//
//        BigDecimal b = new BigDecimal(Double.valueOf(100.00));
//
//        Double amt2 = a.divide(b, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        Double amt4 = a2.divide(b,2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(amt2);
//        System.out.println(amt4);


        System.out.println(((int) (amt3*100)));

        System.out.println(BigDecimal.valueOf(amt3).multiply(new BigDecimal(100)).longValue());
//        BigDecimal.valueOf(amt).divide()

//        System.out.println("01");
//        System.out.print(CheckTxnSwitch(0, 11));
//        System.out.print(CheckTxnSwitch(1, 11));
//        System.out.print(CheckTxnSwitch(2, 11));
//        System.out.println(CheckTxnSwitch(3, 11));
//
//        System.out.println("02");
//        System.out.print(CheckTxnSwitch2(0, 11));
//        System.out.print(CheckTxnSwitch2(1, 11));
//        System.out.print(CheckTxnSwitch2(2, 11));
//        System.out.println(CheckTxnSwitch2(3, 11));

        int number = 10;
        //原始数二进制
        printInfo(number);
        number = number << 1;
        //左移一位
        printInfo(number);
        number = number >> 1;
        //右移一位
        printInfo(number);

//        /* 00000001 << 1 = 00000010 */
//        1 << 1 == 2
//
///* 00000001 << 3 = 00001000 */
//        1 << 3 == 8
//
///* 11111111 11111111 11111111 11110000 >> 4 = 11111111 11111111 11111111 11111111 */
//        0xFFFFFFF0 >> 4 == 0xFFFFFFFF
//
///* 00001111 11111111 11111111 11111111 >> 4 = 00000000 11111111 11111111 11111111 */
//        0x0FFFFFFF >> 4 == 0x00FFFFFF


        /* 从5到15的循环 */
        for (int b = Integer.parseInt("0101",2); b <= Integer.parseInt("1111",2); b++) {
    /* 做些什么 */
        }


        System.out.println(BizCode.BARCODE_CANCEL);
        System.out.println(BizCode.BARCODE_CANCEL.getBizCode());

        EncodeBytes.getBytes();
        JavaIO.JavaIo();
        DataType.dataType();
    }

    public static boolean CheckTxnSwitch(int post, int txnSwtich) {

        //        if ( (txnSwtich << 1 & post) != 0 ) {
        //            return true;
        //        } else {
        //            return false;
        //        }

//        if ( (post << 1 & txnSwtich) != 0 ) {
//            return true;
//        } else {
//            return false;
//        }

//        return ((txnSwtich & (1 << post)) != 0);

        return (((txnSwtich >>> post) & 1) != 0);
    }

    public static boolean CheckTxnSwitch2(int post, int txnSwtich) {

                if ( (txnSwtich << 1 & post) != 0 ) {
                    return true;
                } else {
                    return false;
                }

//        if ( (post << 1 & txnSwtich) != 0 ) {
//            return true;
//        } else {
//            return false;
//        }




    }

    /**
     * 输出一个int的二进制数
     * @param num
     */
    private static void printInfo(int num){
        System.out.println(Integer.toBinaryString(num));
    }
}
