package Union8583;

/**
 *
 * Created by vip on 2018/2/2.
 */
public class PortConfig {

    /**
     * 存放所有接口的配置信息
     * [][0] bit 位:在Map中的位
     * [][1] type 类型:1 ascii 2 binary
     * [][2] len 长度:(对定长有效)
     * [][3] varLen 变长:0非变长 2位变长 3位变长
     */
    public static final int[][] config = {
        {11,1,6,0},
        {12,1,6,0},
        {13,1,4,0},
        {32,1,11,0},
        {37,1,12,0},
        {39,1,2,0},
        {40,2,50,2},
        {41,1,8,0},
        {48,1,52,3},
        {120,2,128,3},
    };
}
