package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DynamicCodeUtils
{
    public static String getDynamicCode(){
        Date data=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s1=sdf.format(data);
        String s2=Integer.toHexString( new Random( ).nextInt(900)+100 );
        return s1+s2;
    }
}
