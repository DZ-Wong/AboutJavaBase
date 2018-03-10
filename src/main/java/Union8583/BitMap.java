package Union8583;

/**
 * 存储每个域信息。
 * Created by vip on 2018/2/2.
 */
public class BitMap {
    private Integer bit;//位
    private Integer bitType;//数据类型 1 ASCII； 2 binary
    private Integer variable;//是否变长 0 定长； 2 两位变长； 3 三位变长
    private Integer len;//数据长度
    private byte[] data;//数据

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public Integer getBitType() {
        return bitType;
    }

    public void setBitType(Integer bitType) {
        this.bitType = bitType;
    }

    public Integer getVariable() {
        return variable;
    }

    public void setVariable(Integer variable) {
        this.variable = variable;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
