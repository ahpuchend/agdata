package cn.edu.seu.agdatab.util;

import java.text.SimpleDateFormat;
import java.util.*;

/***
 * 日期格式化
 *
 */
public class FormatDate {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @param date
     * @return "yyyy-mm-dd"
     */
    public static String getSystemCurrentTime(Date date){
        String dateNowStr = sdf.format(date);
        return dateNowStr;
    }
    /**
     * 出入当前时间，和往前天数，得到以往时间
     * 如 "2020-3-19,-10"--->"2020-3-09"
     * @param date
     * @param bef
     * @return "yyyy-mm-dd"
     */
    public static String getBeforTime(Date date,int bef){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, bef);//正数可以得到当前时间+n天，负数可以得到当前时间-n天
        date = calendar.getTime();
        calendar.setTime(date);
        String beforTime = sdf.format(date);
        return beforTime;
    }
    /**
     * 类似 "yyyy-mm-dd","yyyy-mm-d","yyyy-m-dd","yyyy-m-d"
     * 这四种字符串 全转成 "yyyy-mm-dd"
     * @param stringdate
     */
    public static String stringToData(String stringdate){
        List<Integer> numList = new ArrayList<Integer>();
        numList.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        String[] strings = stringdate.split("-");
        String smm = strings[1];
        String sdd = strings[2];
        try{
            int mm = Integer.parseInt(strings[1]);
            int dd = Integer.parseInt(strings[2]);
            if (numList.contains(mm)) {
                smm = "0" + String.valueOf(mm);
            }
            if(numList.contains(dd)){
                sdd = "0"+String.valueOf(dd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return strings[0] + "-" + smm + "-" + sdd;
        }
    }
}
