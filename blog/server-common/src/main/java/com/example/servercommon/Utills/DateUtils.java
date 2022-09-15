package com.example.servercommon.Utills;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @project: blog
 * @ClassName: DateUtill
 * @author: smallwei
 * @creat: 2022/8/27 17:24
 * 描述:
 */
public class DateUtils {

    /**
     * 返回当前的年月字符串，示例：2021-08
     * @return 年月字符串
     */
    public static String getYearMonth(){
        //yyyyMMdd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date());
    }
}

