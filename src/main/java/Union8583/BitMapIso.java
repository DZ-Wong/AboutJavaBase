package Union8583;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类提供解析请求包和封装信息包两个方法。
 * Created by vip on 2018/2/2.
 */
public class BitMapIso {
    /**
     * 解析请求包
     * @param body
     * @param config
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List unpackRequest(byte[] body, int[][] config) {
        List outList = new ArrayList();
        // 取得除信息类型以外的包信息。也就是取得位图的初始位置。
        byte[] realbody = new byte[body.length - 4];
        System.arraycopy(body, 4, realbody, 0, realbody.length);
        // 取得位图
        byte[] map = null;
        byte[] map8 = new byte[8];
        System.arraycopy(realbody, 0, map8, 0, 8);
        boolean[] bmap8 = LoUtils.getBinaryFromByte(map8);
        if (bmap8[1]) {
            // 如果第一位为1，则是可扩展位图，设为16字节长度。
            map = new byte[16];
            System.arraycopy(realbody, 0, map, 0, 16);
        } else {
            map = map8;
        }
        boolean[] bmap = LoUtils.getBinaryFromByte(map);

        int tmplen = map.length;
        for (int i = 2; i < bmap.length; i++) {
            if (bmap[i]) {
                //BitMap bitMap = null;
                // 寻找位图中的1对应的数据
                int bit=-1;
                for (int j = 0; j < config.length; j++) {
                    if (config[j][0] == i) {
                        bit=j;
                        break;
                    }
                }
                BitMap outBitMap = new BitMap();
                outBitMap.setBit(i);
                outBitMap.setBitType(config[bit][1]);
                //len对变长是无用的。
                outBitMap.setLen(config[bit][2]);
                outBitMap.setVariable(config[bit][3]);
                byte[] nextData = null;
                if (config[bit][3] > 0) {
                    //取出变长部分的值。
                    int varLen = config[bit][3];
                    if (config[bit][1] == 2) {
                        varLen = varLen - 1;
                    }
                    byte[] varValue = new byte[varLen];
                    System.arraycopy(realbody, tmplen, varValue, 0, varValue.length);
                    int datLen = 0;
                    if (config[bit][1] == 2) {
                        datLen = LoUtils.bcdToint(varValue);
                    } else {
                        datLen = LoUtils.bytes2int(varValue);
                    }

                    tmplen += varLen;
                    // 取出变长部分后带的值。
                    nextData = new byte[datLen];

                    System.arraycopy(realbody, tmplen, nextData, 0,nextData.length);
                    tmplen += nextData.length;
                } else {
                    nextData = new byte[config[bit][2]];
                    System.arraycopy(realbody, tmplen, nextData, 0,nextData.length);
                    tmplen += config[bit][2];
                }
                outBitMap.setData(nextData);
                outList.add(outBitMap);
            }
        }

        return outList;
    }

    /**
     * 打包响应包,不包括消息类型
     * @param list
     * @return byte[]
     */
    @SuppressWarnings("unchecked")
    public static byte[] PackResponse(List list) {
        int len = 16;
        for (int i = 0; i < list.size(); i++) {
            BitMap bitMap = (BitMap) list.get(i);
            // 计算请求包总长度
            if (bitMap.getBitType() == 2) {
                if (bitMap.getVariable() > 0) {
                    len += bitMap.getVariable() - 1 + bitMap.getData().length;
                } else {
                    len += bitMap.getVariable() + bitMap.getData().length;
                }
            } else {
                len += bitMap.getVariable() + bitMap.getData().length;
            }
        }
        byte[] body = new byte[len];
        // 位图
        boolean[] bbitMap = new boolean[129];
        bbitMap[1] = true;
        int temp = (bbitMap.length - 1) / 8;
        for (int j = 0; j < list.size(); j++) {
            BitMap bitMap = (BitMap) list.get(j);
            bbitMap[bitMap.getBit()] = true;
            byte[] bitmap = LoUtils.getByteFromBinary(bbitMap);
            System.arraycopy(bitmap, 0, body, 0, bitmap.length);
            // 数据
            if (bitMap.getVariable() > 0) {
                // 数据是可变长的:拼变长的值
                byte[] varValue = null;
                if (bitMap.getBitType() == 2) {
                    varValue = LoUtils.StrToBCDBytes(String.format("%0"+ bitMap.getVariable() + "d",bitMap.getData().length));
                } else {
                    varValue = String.format("%0" + bitMap.getVariable() + "d",bitMap.getData().length).getBytes();
                }
                System.arraycopy(varValue, 0, body, temp, varValue.length);
                temp += varValue.length;
                // 拼变长部分后所带的数的值。
                System.arraycopy(bitMap.getData(), 0, body, temp, bitMap.getData().length);
                temp += bitMap.getData().length;
            } else {
                // 数据是固定长度的。
                byte dat[] =new byte[bitMap.getLen()];
                if (bitMap.getData().length!=bitMap.getLen()){
                    System.arraycopy(bitMap.getData(), 0, dat, 0, bitMap.getLen());
                }else{
                    dat=bitMap.getData();
                }
                System.arraycopy(dat, 0, body, temp, dat.length);
                temp += bitMap.getData().length;
            }
        }
        return body;
    }
}
