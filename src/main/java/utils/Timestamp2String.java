package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * timestamp 和 string 互转。
 * Created by vip on 2018/2/23.
 */
public class Timestamp2String {


    public void Timestamp2String(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now  = new Timestamp(System.currentTimeMillis());
        String str = df.format(now);
        System.out.println(str);
    }

    public void String2Timestamp(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        System.out.println(ts);
     }
    public static final void main(String[] args){

        Timestamp2String TS = new Timestamp2String();
        TS.Timestamp2String();
        TS.String2Timestamp();


    }
}
