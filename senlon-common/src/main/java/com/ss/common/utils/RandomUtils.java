package com.ss.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomUtils {
    public static Long getRandomNum(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(date);
        String str = now.replace("-","").replace(" ","").replace(":","");
        long randNum = Long.parseLong(str);
        return randNum;
    }

}
