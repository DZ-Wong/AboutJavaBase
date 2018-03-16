package T0308.Base;
//
//import io.netty.buffer.ByteBuf;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Utils {

//	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	private  static  Map<String,String> respMsg = new HashMap<String,String>();
	
	
	/**
	 * 获得系统安装路径
	 * @Title: getRootPath
	 * @Description: TODO
	 * @param @return  
	 * @return String   
	 * @throws
	 */
	public static String getRootPath() {
		
		File file=new File("");
		String home = file.getAbsolutePath();
		if (home.indexOf(":") == -1) {//linux
			home = "/" + home;
		}
		
		return home;
	}

	/**
	 * 字节数组转16进制
	 * 
	 * @Title: byte2hex
	 * @Description: TODO
	 * @param @param b
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String byte2hex(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder("");
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			stringBuilder.append(hex.toUpperCase());
			// stringBuilder.append(" ");
		}
		return stringBuilder.toString();
	}

	/**
	 * 16进制转byte[]
	 * 
	 * @Title: hex2byte
	 * @Description: TODO
	 * @param @param hexString
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] hex2byte(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		// 奇数前面补0
		if (hexString.length() % 2 != 0) {
			hexString = "0" + hexString;
		}

		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}

		if (d.length == 0) {
			d = new byte[] { 0 };
		}

		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 16进制转字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String hex2asc(String s) {
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}

		StringBuffer sb = new StringBuffer("");
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int code = Integer.parseInt(
					String.valueOf(cs[i]) + String.valueOf(cs[i + 1]), 16);
			sb.append((char) code);
		}
		return sb.toString();
	}

	/**
	 * 把ByteBuf转换成byte[]
	 * 
	 * @Title: convertByteBuf
	 * @Description: TODO
	 * @param @param msg
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] convertByteBuf(ByteBuf msg) {
		byte[] msgBytes = new byte[msg.readableBytes()];
		int readerIndex = msg.readerIndex();
		msg.getBytes(readerIndex, msgBytes);
		
		return msgBytes;
	}

	/**
	 * 计算LRC
	 * 
	 * @Title: LRC
	 * @Description: TODO
	 * @param @param bytes
	 * @param @param length
	 * @param @return
	 * @return int
	 * @throws
	 */
	public static int LRC(byte[] bytes) {
		int checksum = 0;
		for (int i = 0; i < bytes.length; i++) {
			checksum ^= (bytes[i] & 0xFF);
		}
		return checksum;
	}

	/**
	 * bcd编码
	 * 
	 * @Title: str2cbcd
	 * @Description: TODO
	 * @param @param s
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] str2bcd(String s) {
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int high = cs[i] - 48;
			int low = cs[i + 1] - 48;
			baos.write(high << 4 | low);
		}
		return baos.toByteArray();
	}

	/**
	 * bcd解码
	 * 
	 * @Title: cbcd2string
	 * @Description: TODO
	 * @param @param b
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String bcd2str(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			int h = ((b[i] & 0xff) >> 4) + 48;
			sb.append((char) h);
			int l = (b[i] & 0x0f) + 48;
			sb.append((char) l);
		}
		return sb.toString();
	}

	/**
	 * 
	 * 
	 * @Title: getLrcBytes
	 * @Description: TODO
	 * @param @param msg
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] getLrcBytes(byte msg[]) {
		int stPos = 4;
		int cutLen = msg.length - stPos - 2;
		byte caculMsg[] = new byte[cutLen];

		System.arraycopy(msg, stPos, caculMsg, 0, cutLen);

		return caculMsg;
	}

	/**
	 * 从报文中提取字段值
	 * 
	 * @Title: getFiledByte
	 * @Description: TODO
	 * @param @param msg 业务报文
	 * @param @param stPos 起始位置
	 * @param @param len 字段长度
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] getFiledByte(byte msg[], int stPos, int len) {
		byte fieldByte[] = new byte[len];
		System.arraycopy(msg, stPos, fieldByte, 0, len);

		return fieldByte;
	}
	
	/**
	 * 字节数组转int
	 * @Title: byte4ToInt
	 * @Description: TODO
	 * @param @param bytes
	 * @param @param off
	 * @param @return  
	 * @return int   
	 * @throws
	 */
	public static int byte2Int(byte[] b, int off) 
	{  
		  int mask=0xff;  
          int temp=0;  
          int n=0;  
          for(int i=0;i<b.length;i++)
          {  
             n<<=8;  
             temp=b[i]&mask;  
             n|=temp;  
          }  
          
          return n;
	}
	
	/**
	 * 把整数转成4位字节
	 * @Title: int2Byte
	 * @Description: TODO
	 * @param @param i
	 * @param @return  
	 * @return byte[]   
	 * @throws
	 */
	public static byte[] int2Byte(int num) 
	{  
		byte result[] = new byte[4];
		int byteLen = 4;

		byte b[] = Utils.hex2byte(Integer.toHexString(num));
		int j=0;
		for (int i=0; i<byteLen; i++)
		{
			if (i<(byteLen-b.length))
			{
				result[i] = (byte)0;
			}
			else
			{
				result[i] = b[j++];
			}
		}
		
		
        return result;
	}
	
	/**
	 * 转换银联返回错误信息
	 * @Title: convertPayRespCode
	 * @Description: TODO
	 * @param @param code
	 * @param @return  
	 * @return String   
	 * @throws
	 */
	public static String convertPayRespCode(String code) 
	{
		//如果已初始化，则直接查询返回，否则设值后再返回
		if(!respMsg.isEmpty()){
			return respMsg.get(code);
		}
		respMsg.put("00", "交易成功");
		respMsg.put("01", "请持卡人与发卡银行联系");
		respMsg.put("03", "无效商户");
		respMsg.put("04", "此卡被没收");
		respMsg.put("05", "持卡人认证失败");
		respMsg.put("10", "显示部分批准金额，提示操作员");
		respMsg.put("11", "成功，VIP客户");
		respMsg.put("12", "无效交易");
		respMsg.put("13", "无效金额");
		respMsg.put("14", "无效卡号");
		respMsg.put("15", "此卡无对应发卡方");
		respMsg.put("22", "操作有误，或超出交易允许天数");
		respMsg.put("25", "没有原始交易，请联系发卡方");
		respMsg.put("30", "请重试");
		respMsg.put("34", "作弊卡,呑卡");
		respMsg.put("38", "密码错误次数超限，请与发卡方联系");
		respMsg.put("40", "发卡方不支持的交易类型");
		respMsg.put("41", "挂失卡，请没收（POS）");
		respMsg.put("43", "被窃卡，请没收");
		respMsg.put("51", "可用余额不足");
		respMsg.put("54", "该卡已过期");
		respMsg.put("55", "密码错");
		respMsg.put("57", "不允许此卡交易");
		respMsg.put("58", "发卡方不允许该卡在本终端进行此交易");
		respMsg.put("59", "卡片校验错");
		respMsg.put("61", "交易金额超限");
		respMsg.put("62", "受限制的卡");
		respMsg.put("64", "交易金额与原交易不匹配");
		respMsg.put("65", "超出消费次数限制");
		respMsg.put("68", "交易超时，请重试");
		respMsg.put("75", "密码错误次数超限");
		respMsg.put("90", "系统日切，请稍后重试");
		respMsg.put("91", "发卡方状态不正常，请稍后重试");
		respMsg.put("92", "发卡方线路异常，请稍后重试");
		respMsg.put("94", "拒绝，重复交易，请稍后重试");
		respMsg.put("96", "拒绝，交换中心异常，请稍后重试");
		respMsg.put("97", "终端未登记");
		respMsg.put("98", "发卡方超时");
		respMsg.put("99", "PIN格式错，请重新签到");
		respMsg.put("A0", "MAC校验错，请重新签到");
		respMsg.put("A1", "转账货币不一致");
		respMsg.put("A2", "交易成功，请向发卡行确认");
		respMsg.put("A3", "账户不正确");
		respMsg.put("A4", "交易成功，请向发卡行确认");
		respMsg.put("A5", "交易成功，请向发卡行确认");
		respMsg.put("A6", "交易成功，请向发卡行确认");
		respMsg.put("A7", "拒绝，交换中心异常，请稍后重试");
		
		respMsg.put("B1", "刷卡失败");
		respMsg.put("B2", "交易人为取消");
		
		respMsg.put("B3", "取消输密码");
		respMsg.put("B5", "原交易不存在");
		respMsg.put("B6", "原交易不符合条件");
		respMsg.put("B7", "POS未联机签到");
		respMsg.put("B8", "POSP连接失败");
		respMsg.put("B9", "不支持的卡类型");
		respMsg.put("X1", "获取密码失败");
		respMsg.put("X2", "密码加密失败");
		respMsg.put("X3", "密码键盘激活密钥失败");
		respMsg.put("X4", "交易异常失败");
		respMsg.put("X5", "打包失败");
		respMsg.put("X6", "返回消息类型不对");
		respMsg.put("X7", "主机报文的mac错");
		respMsg.put("X8", "写流水失败");
		respMsg.put("X9", "结算失败");
		respMsg.put("XA", "存储满");
		respMsg.put("XB", "IC卡交易拒绝");
		respMsg.put("XC", "保存冲正数据失败");
		respMsg.put("XD", "数据发送失败");
		respMsg.put("XE", "数据接收失败");
		respMsg.put("XF", "解包失败");
		respMsg.put("B4", "冲正失败");
		//二维码
		respMsg.put("G1", "电子券可支付金额不足");
		respMsg.put("G3", "base64二维码图片出错");
		respMsg.put("H0", "活动不存在");
		respMsg.put("H1", "电子券刷卡消费通讯渠道错误");
		respMsg.put("H2", "电子券重复");
		respMsg.put("H3", "电子券不存在");
		respMsg.put("H4", "兑换码不正确");
		respMsg.put("K7", "门店不存在");
		respMsg.put("K8", "券门店列表为空，不允许在该门店使用");
		respMsg.put("K9", "券不允许在该门店使用");
		respMsg.put("F5", "消费失败");
		respMsg.put("F4", "无手机号电子券消费失败");
		respMsg.put("F3", "创建电子券消费流水失败");
		respMsg.put("D5", "电子券已经过期");
		respMsg.put("D4", "电子券还不能使用");
		respMsg.put("D3", "电子券已作废,不可消费");
		respMsg.put("D2", "电子券发送状态为：发送失败,数据异常,不可消费");
		respMsg.put("D1", "电子券发送状态为：待发送,数据异常,不可消费");
		respMsg.put("D0", "电子券发送状态为：不发送,数据异常,不可消费");
		respMsg.put("C9", "该券已过期,不可以消费");
		respMsg.put("C8", "该券的余额为0,不可以消费");
		respMsg.put("C7", "该券的剩余次数为0,不可以消费");
		respMsg.put("C6", "该券已转让,不可以消费");
		respMsg.put("C5", "交易的电子券数量不可用，必须是整数且大于0");
		respMsg.put("C4", "交易的电子券数量不可用，必须是整数且大于0");
		respMsg.put("C1", "该电子券类型属于非电子会员卡，请到【用券】处消费");
		respMsg.put("C0", "参数不能为空");
		// to CPOS  client API  
		respMsg.put("011", "POS机返回结果超时");
		respMsg.put("012", "与POS机之间连接信道异常");
		respMsg.put("013", "取消刷卡异常");
		respMsg.put("014", "与POS机信道正常，但写入数据异常");
		return respMsg.get(code);
	}
	
	/**
	 * 获得当前日期
	 * @Title: getNowDateStrYYMMDD
	 * @Description: TODO
	 * @param @return  
	 * @return String   
	 * @throws
	 */
	public static String getNowDateStrYYMMDD()
	{
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); //格式化当前系统日期
		String dateTime = dateFm.format(new java.util.Date());
		
		return dateTime;
	}

	/**
	 * 从address提取ip
	 * @Title: getIp
	 * @Description: TODO
	 * @param @param address
	 * @param @return  
	 * @return String   
	 * @throws
	 */
	public static String getIpFromAddress(String address)
	{
		//     /192.168.8.247:4400
		return address.split(":")[0].substring(1);
	}
	
	/**
	 * 从address提取port
	 * @Title: getIp
	 * @Description: TODO
	 * @param @param address
	 * @param @return  
	 * @return String   
	 * @throws
	 */
	public static String getIpFromPort(String address)
	{
		//     /192.168.8.247:4400
		return address.split(":")[1];
	}
	
	/**
	 * 获取本地ip
	 * @Title: getLocalIp
	 * @Description: TODO
	 * @param @return  
	 * @return String   
	 * @throws
	 */
	public static String getLocalIp()
	{
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {

//			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	/**
	 * 两个字符串是否都不为空
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean  strIsNotEmpty(String str1,String str2){
		int  i = 0;
		if(str1!=null && str1.trim().length()>0){
			i++;
		}
		if(str2!=null && str2.trim().length()>0){
			i++;
		}
		return i==2?true:false;
	}
	
	public static boolean  strIsNotEmpty(String str1){
		if(str1!=null && str1.trim().length()>0){
			return true;
		}
		return false;
	}
	
	public static void writeFile(String path,String content)
	{
		BufferedWriter bw = null;
		
		 try
		 {
			 File file = new File(path);

			 if (!file.exists()) 
			 {
				 file.createNewFile();
			 }

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (Exception e) {
//			logger.error(e.getMessage());
		}
		 finally
		 {
			 if (bw!=null)
			 {
				 try {
					bw.close();
				} catch (IOException e) {
				}
			 }
		 }
	}

	
	
	public static void main(String ars[]) throws IOException {

		// System.out.println(
		// Arrays.toString(Utils.hex2byte(Integer.toHexString(0))) );
		// System.out.println(Arrays.toString(outBytesStream.toByteArray()));
		// int lrc = Utils.LRC(
		// Utils.hex2byte("010461326563313962352D616333392D343161612D626233342D6135383038313463616334393333")
		// );
		// String h = Integer.toHexString(8);
		// byte b = Utils.hex2byte(h)[0];
		// System.out.println( b );

//		int lrc = Utils
//				.LRC(Utils
//						.hex2byte("A00330663463653761332D366462632D343661342D623264392D323865656466646462336533"));
//		byte lrcByte = Utils.hex2byte(Integer.toHexString(lrc))[0];
//		System.out.println(Utils.byte2hex(new byte[] { lrcByte }));
		
//		System.out.println(Utils.getIpFromAddress("/192.168.8.247:4400"));
//		System.out.println(Utils.getIpFromPort("/192.168.8.247:4400"));
		
//		String ttt = "7B226D6574686F64223A2230313033222C2270617261223A227B5C227465726D4E6F5C223A5C2231323334353637385C222C5C22616D6F756E745C223A34352E352C5C226D696C656167655C223A302C5C22706F696E747354746C5C223A302C5C22636F6E73756D6544657461696C4C6973745C223A7B5C22636F6E73756D6544657461696C735C223A5B7B5C226B696E6449445C223A32372C5C226B696E646E616D655C223A5C225C222C5C226B696E64646570745C223A5C225C222C5C22436F756E74416D6F756E745C223A302C5C2246436F756E74416D6F756E745C223A302C5C2246436F756E745C223A302C5C2270726963655C223A31372E32332C5C22676F7650726963655C223A302C5C226D696E50726963655C223A302C5C226661766F757250726963655C223A302C5C227174795C223A313530302C5C22616D6F756E745C223A302C5C2264697363416D6F756E745C223A312E352C5C226661766F757261626C654D6F64655C223A5C22305C222C5C226661766F724E756D5C223A5C22305C222C5C22697357686572655C223A302C5C22706F696E74735C223A307D2C7B5C226B696E6449445C223A31342C5C226B696E646E616D655C223A5C225C222C5C226B696E64646570745C223A5C225C222C5C22436F756E74416D6F756E745C223A302C5C2246436F756E74416D6F756E745C223A302C5C2246436F756E745C223A302C5C2270726963655C223A372C5C22676F7650726963655C223A302C5C226D696E50726963655C223A302C5C226661766F757250726963655C223A302C5C227174795C223A313030302C5C22616D6F756E745C223A302C5C2264697363416D6F756E745C223A312E322C5C226661766F757261626C654D6F64655C223A5C22305C222C5C226661766F724E756D5C223A5C22305C222C5C22697357686572655C223A302C5C22706F696E74735C223A307D2C7B5C226B696E6449445C223A31322C5C226B696E646E616D655C223A5C225C222C5C226B696E64646570745C223A5C225C222C5C22436F756E74416D6F756E745C223A302C5C2246436F756E74416D6F756E745C223A302C5C2246436F756E745C223A302C5C2270726963655C223A332E352C5C22676F7650726963655C223A302C5C226D696E50726963655C223A302C5C226661766F757250726963655C223A302C5C227174795C223A323030302C5C22616D6F756E745C223A302C5C2264697363416D6F756E745C223A302C5C226661766F757261626C654D6F64655C223A5C22305C222C5C226661766F724E756D5C223A5C22305C222C5C22697357686572655C223A302C5C22706F696E74735C223A307D5D7D7D222C226264617465223A22323031342D30392D3137222C22776F726B5368696674223A2233227D";
//		String ttt2 = "7B226D6574686F64223A2230313033222C2270617261223A227B5C227465726D4E6F5C223A5C2231323334353637385C222C5C22616D6F756E745C223A3134352E352C5C226D696C656167655C223A302C5C22706F696E747354746C5C223A302C5C22636F6E73756D6544657461696C4C6973745C223A7B5C22636F6E73756D6544657461696C735C223A5B7B5C226B696E6449445C223A32372C5C226B696E646E616D655C223A5C225C222C5C226B696E64646570745C223A5C225C222C5C22436F756E74416D6F756E745C223A302C5C2246436F756E74416D6F756E745C223A302C5C2246436F756E745C223A302C5C2270726963655C223A31372E32332C5C22676F7650726963655C223A302C5C226D696E50726963655C223A302C5C226661766F757250726963655C223A302C5C227174795C223A313530302C5C22616D6F756E745C223A302C5C2264697363416D6F756E745C223A312E352C5C226661766F757261626C654D6F64655C223A5C22305C222C5C226661766F724E756D5C223A5C22305C222C5C22697357686572655C223A302C5C22706F696E74735C223A307D2C7B5C226B696E6449445C223A31342C5C226B696E646E616D655C223A5C225C222C5C226B696E64646570745C223A5C225C222C5C22436F756E74416D6F756E745C223A302C5C2246436F756E74416D6F756E745C223A302C5C2246436F756E745C223A302C5C2270726963655C223A372C5C22676F7650726963655C223A302C5C226D696E50726963655C223A302C5C226661766F757250726963655C223A302C5C227174795C223A313030302C5C22616D6F756E745C223A302C5C2264697363416D6F756E745C223A312E322C5C226661766F757261626C654D6F64655C223A5C22305C222C5C226661766F724E756D5C223A5C22305C222C5C22697357686572655C223A302C5C22706F696E74735C223A307D2C7B5C226B696E6449445C223A31322C5C226B696E646E616D655C223A5C225C222C5C226B696E64646570745C223A5C225C222C5C22436F756E74416D6F756E745C223A302C5C2246436F756E74416D6F756E745C223A302C5C2246436F756E745C223A302C5C2270726963655C223A332E352C5C22676F7650726963655C223A302C5C226D696E50726963655C223A302C5C226661766F757250726963655C223A302C5C227174795C223A323030302C5C22616D6F756E745C223A302C5C2264697363416D6F756E745C223A302C5C226661766F757261626C654D6F64655C223A5C22305C222C5C226661766F724E756D5C223A5C22305C222C5C22697357686572655C223A302C5C22706F696E74735C223A307D5D7D7D222C226264617465223A22323031342D30392D3137222C22776F726B5368696674223A2233227D";
//		
//		byte bbb[] = Utils.hex2byte(ttt2);
//		
//		System.out.println(new String(bbb));
		
		//System.out.println(Utils.byte2Int(Utils.hex2byte("000003FF"), 0));
		//String hex = "787000000001767200106A6176612E6C616E672E537472696E67A0F0A4387A3BB3420200007870757200135B4C6A6176612E6C616E672E4F626A6563743B90CE589F1073296C020000787000000001740006E59388E59388";
		//String hex2 = "ACED0005770A000873617948656C6C6F757200125B4C6A6176612E6C616E672E436C6173733BAB16D7AECBCD5A99020000787000000001767200106A6176612E6C616E672E537472696E67A0F0A4387A3BB3420200007870757200135B4C6A6176612E6C616E672E4F626A6563743B90CE589F1073296C020000787000000001740006E59388E59388";
				
		//System.out.println(new String(Utils.hex2byte(hex)));
		//System.out.println(new String(Utils.hex2byte(hex2)));
		
		//byte b[] = new byte[]{123, 34, 114, 101, 115, 112, 67, 111, 110, 116, 34, 58, 34, 92, 34, 99, 111, 109, 46, 99, 104, 105, 110, 97, 101, 120, 112, 114, 101, 115, 115, 99, 97, 114, 100, 46, 101, 120, 99, 101, 112, 116, 105, 111, 110, 46, 116, 114, 97, 100, 101, 46, 80, 97, 114, 97, 69, 120, 99, 101, 112, 116, 105, 111, 110, 58, 32, 92, 92, 117, 48, 48, 48, 48, 92, 92, 117, 48, 48, 48, 48, 110, 117, 108, 108, 92, 34, 34, 44, 34, 114, 101, 115, 112, 67, 111, 100, 101, 34, 58, 34, 48, 49, 34, 125};
		
		//System.out.println(Utils.byte2hex(b));
		
		byte s[] = Utils.hex2byte("474554202F3F613D6E20485454502F312E310D0A486F73743A203137322E31362E3130342E343A383038300D0A557365722D4167656E743A204D6F7A696C6C612F352E30202857696E646F7773204E5420362E313B20574F5736343B2072763A33352E3029204765636B6F2F32303130303130312046697265666F782F33352E300D0A4163636570743A20746578742F68746D6C2C6170706C69636174696F6E2F7868746D6C2B786D6C2C6170706C69636174696F6E2F786D6C3B713D302E392C2A2F2A3B713D302E380D0A4163636570742D4C616E67756167653A207A682D636E2C7A683B713D302E382C656E2D75733B713D302E352C656E3B713D302E330D0A4163636570742D456E636F64696E673A20677A69702C206465666C6174650D0A436F6F6B69653A204A53455353494F4E49443D33364446354633424441393846433645344330363234333645313133353736340D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A0D0A");
		System.out.println(new String(s));
		

	}
	

}





