package utils;

/**
 * Created by vip on 2018/2/26.
 */
public enum BizCode {
    BARCODE_PAY(0, "841100"),
    BARCODE_MAN_QUERY(1, "841110"),
    BARCODE_QUERY(2, "841120"),
    BARCODE_REFUND(3, "841200"),
    BARCODE_CANCEL(4, "841500");

    private int num;
    private String bizCode;
    BizCode(int num, String bizCode) {
        this.num = num;
        this.bizCode = bizCode;
    }

    public int getNum() {
        return num;
    }

    public String getBizCode() {
        return bizCode;
    }
}
