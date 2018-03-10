package Union8583;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 编码与数据类型类。
 * Created by vip on 2018/2/2.
 */
public class LoUtils {
    private static BASE64Encoder encoder = new BASE64Encoder ();
    private static BASE64Decoder decoder = new BASE64Decoder ();

    /**
     * BASE64 编码
     *
     * @param
     * @return
     */
    public static String encodeBufferBase64(byte [] buff)
    {
        return buff == null ? null : encoder.encode (buff);
    }

    /**
     * BASE64解码
     *
     * @param s
     * @return
     */
    public static byte [] decodeBufferBase64(String s)
    {
        try
        {
            return s == null ? null : decoder.decodeBuffer (s);
        }catch (IOException e)
        {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * BASE64 字节数组编码
     *
     * @param s
     * @return String
     */
    public static String encodeBase64(byte [] s)
    {
        if(s == null)
            return null;
        String res = new BASE64Encoder().encode (s);
        res = res.replace ("\n","");
        res = res.replace ("\r","");
        return res;
    }

    /**
     * BASE64解码
     *
     * @param
     * @return
     */
    public static byte [] decodeBase64(byte [] buff)
    {
        if(buff == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            byte [] b = decoder.decodeBuffer (new String (buff));

            return b;
        }catch (Exception e)
        {
            return null;
        }
    }

//    /**
//     * 将reauest里的数据包转成字符串
//     *
//     * @param request
//     * @return String
//     */
//    public static String getRequestBodyTxt(HttpServletRequest request)
//    {
//        // 接收手机传过来的参数
//        BufferedInputStream bufferedInputStream = null;
//        // 此类实现了一个输出流，其中的数据被写入一个字节数组
//        ByteArrayOutputStream bytesOutputStream = null;
//        String body = null;
//        try
//        {
//
//            // BufferedInputStream 输入流
//            bufferedInputStream = new BufferedInputStream (
//                request.getInputStream ());
//            bytesOutputStream = new ByteArrayOutputStream ();
//            // 写入数据
//            int ch;
//            while ((ch = bufferedInputStream.read ()) != -1)
//            {
//                bytesOutputStream.write (ch);
//            }
//            // 转换为String类型
//            body = new String (bytesOutputStream.toByteArray (),"UTF-8");
//        }catch (Exception ex)
//        {
//            ex.printStackTrace ();
//        }
//        finally
//        {
//            // 关闭此输入流并释放与该流关联的所有系统资源。
//            try
//            {
//                bytesOutputStream.flush ();
//                bytesOutputStream.close ();
//                bufferedInputStream.close ();
//            }catch (IOException e)
//            {
//                e.printStackTrace ();
//            }
//        }
//        return body;
//    }
//
//    /**
//     * 将reauest里的数据包转成字节数组
//     *
//     * @param request
//     * @return
//     */
//    public static byte [] getRequestBodyByte(HttpServletRequest request)
//    {
//        HttpS
//        // 接收手机传过来的参数
//        BufferedInputStream bufferedInputStream = null;
//        // 此类实现了一个输出流，其中的数据被写入一个字节数组
//        ByteArrayOutputStream bytesOutputStream = null;
//        byte [] body = null;
//        try
//        {
//            // BufferedInputStream 输入流
//            bufferedInputStream = new BufferedInputStream (
//                request.getInputStream ());
//            bytesOutputStream = new ByteArrayOutputStream ();
//            // 写入数据
//            int ch;
//            while ((ch = bufferedInputStream.read ()) != -1)
//            {
//                bytesOutputStream.write (ch);
//            }
//            // 转换为String类型
//            body = bytesOutputStream.toByteArray ();
//        }catch (Exception ex)
//        {
//            ex.printStackTrace ();
//        }
//        finally
//        {
//            // 关闭此输入流并释放与该流关联的所有系统资源。
//            try
//            {
//                bytesOutputStream.flush ();
//                bytesOutputStream.close ();
//                bufferedInputStream.close ();
//            }catch (IOException e)
//            {
//                e.printStackTrace ();
//            }
//        }
//        return body;
//    }

    public static String getEigthBitsStringFromByte(int b)
    {
        // if this is a positive number its bits number will be less
        // than 8
        // so we have to fill it to be a 8 digit binary string
        // b=b+100000000(2^8=256) then only get the lower 8 digit
        b |= 256; // mark the 9th digit as 1 to make sure the string
        // has at
        // least 8 digits
        String str = Integer.toBinaryString (b);
        int len = str.length ();
        return str.substring (len - 8,len);
    }

    public static byte getByteFromEigthBitsString(String str)
    {
        // if(str.length()!=8)
        // throw new Exception("It's not a 8 length string");
        byte b;
        // check if it's a minus number
        if(str.substring (0,1).equals ("1"))
        {
            // get lower 7 digits original code
            str = "0" + str.substring (1);
            b = Byte.valueOf (str,2);
            // then recover the 8th digit as 1 equal to plus
            // 1000000
            b |= 128;
        }
        else
        {
            b = Byte.valueOf (str,2);
        }
        return b;
    }

    /**
     * 将一个16字节数组转成128二进制数组
     *
     * @param b
     * @return
     */
    public static boolean [] getBinaryFromByte(byte [] b)
    {
        boolean [] binary = new boolean [b.length * 8 + 1];
        String strsum = "";
        for (int i = 0;i < b.length;i++ )
        {
            strsum += getEigthBitsStringFromByte (b [i]);
        }
        for (int i = 0;i < strsum.length ();i++ )
        {
            if(strsum.substring (i,i + 1).equalsIgnoreCase ("1"))
            {
                binary [i + 1] = true;
            }
            else
            {
                binary [i + 1] = false;
            }
        }
        return binary;
    }

    /**
     * 将一个128二进制数组转成16字节数组
     *
     * @param binary
     * @return
     */
    public static byte [] getByteFromBinary(boolean [] binary)
    {

        int num = (binary.length - 1) / 8;
        if((binary.length - 1) % 8 != 0)
        {
            num = num + 1;
        }
        byte [] b = new byte [num];
        String s = "";
        for (int i = 1;i < binary.length;i++ )
        {
            if(binary [i])
            {
                s += "1";
            }
            else
            {
                s += "0";
            }
        }
        String tmpstr;
        int j = 0;
        for (int i = 0;i < s.length ();i = i + 8)
        {
            tmpstr = s.substring (i,i + 8);
            b [j] = getByteFromEigthBitsString (tmpstr);
            j = j + 1;
        }
        return b;
    }

    /**
     * 将一个byte位图转成字符串
     *
     * @param b
     * @return
     */
    public static String getStrFromBitMap(byte [] b)
    {
        String strsum = "";
        for (int i = 0;i < b.length;i++ )
        {
            strsum += getEigthBitsStringFromByte (b [i]);
        }
        return strsum;
    }

    /**
     * bytes转换成十六进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2HexStr(byte [] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0;n < b.length;n++ )
        {
            stmp = (Integer.toHexString (b [n] & 0XFF));
            if(stmp.length () == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase ();
    }

    private static byte uniteBytes(String src0, String src1)
    {
        byte b0 = Byte.decode ("0x" + src0).byteValue ();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode ("0x" + src1).byteValue ();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    /**
     * 十六进制字符串转换成bytes
     *
     * @param src
     * @return
     */
    public static byte [] hexStr2Bytes(String src)
    {
        int m = 0, n = 0;
        int l = src.length () / 2;
        byte [] ret = new byte [l];
        for (int i = 0;i < l;i++ )
        {
            m = i * 2 + 1;
            n = m + 1;
            ret [i] = uniteBytes (src.substring (i * 2,m),
                                  src.substring (m,n));
        }
        return ret;
    }

    /**
     * 将String转成BCD码
     *
     * @param s
     * @return
     */
    public static byte [] StrToBCDBytes(String s)
    {

        if(s.length () % 2 != 0)
        {
            s = "0" + s;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream ();
        char [] cs = s.toCharArray ();
        for (int i = 0;i < cs.length;i += 2)
        {
            int high = cs [i] - 48;
            int low = cs [i + 1] - 48;
            baos.write (high << 4 | low);
        }
        return baos.toByteArray ();
    }

    /**
     * 将BCD码转成int
     *
     * @param b
     * @return
     */
    public static int bcdToint(byte [] b)
    {
        StringBuffer sb = new StringBuffer ();
        for (int i = 0;i < b.length;i++ )
        {
            int h = ((b [i] & 0xff) >> 4) + 48;
            sb.append ((char) h);
            int l = (b [i] & 0x0f) + 48;
            sb.append ((char) l);
        }
        return Integer.parseInt (sb.toString ());
    }

    //高位在前，低位在后
    public static byte[] int2bytes(int num){
        byte[] result = new byte[4];
        result[0] = (byte)((num >>> 24) & 0xff);//说明一
        result[1] = (byte)((num >>> 16)& 0xff );
        result[2] = (byte)((num >>> 8) & 0xff );
        result[3] = (byte)((num >>> 0) & 0xff );
        return result;
    }

    //高位在前，低位在后
    public static int bytes2int(byte[] bytes){
        int result = 0;
        if(bytes.length == 4){
            int a = (bytes[0] & 0xff) << 24;//说明二
            int b = (bytes[1] & 0xff) << 16;
            int c = (bytes[2] & 0xff) << 8;
            int d = (bytes[3] & 0xff);
            result = a | b | c | d;
        }
        return result;
    }

    /**
     * 输出调试信息
     *
     * @param str
     */
    public static void trace(String str)
    {
        // System.out.println ("["
        // + (new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.S")).format (new Date ())
        // + "]>" + str);
    }
}
