package util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class FormatDateTest {

    @Test
    public void getSystemCurrentTime(){
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        System.out.println("格式化后的日期：" + dateNowStr);

    }
    @Test
    public void getBeforTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -10);//正数可以得到当前时间+n天，负数可以得到当前时间-n天
        date = calendar.getTime();
        System.out.println("获取当前时间未来的第三天：" + date);
        calendar.setTime(date);
        String time = sdf.format(date);
        System.out.println("格式化获取当前时间未来的第三天：" + time);
    }
    @Test
    public void GetProvice(){
        String provinceName = "安徽省".substring(0,2);
        System.out.println(provinceName);
    }
    @Test
    public void eleprint() throws IOException {
        String URL = "http://www.vegnet.com.cn/Price";
        Document doc = null;
        doc = Jsoup.connect(URL).get();
        Elements elements = doc.select("div.pricebuxright dd>a");
//        System.out.println(elements);
//        ele = elements.first();
        for(Element ele : elements ) {
            System.out.println(ele.attr("href"));
        }
    }
    @Test
    public void containOne(){
        LinkedList<String> ll = new LinkedList<String>();
        ll.add("2020-03-27");
        ll.add("2020-03-28");
        ll.add("2020-03-29");
        if(ll.contains("2020-03-29")){
            System.out.println("包含2020-03-29");
        }else{
            System.out.println("bu包含2020-03-29");
        }

        if (ll.contains("2020-03-30")){
            System.out.println("包含2020-03-30");
        }else {
            System.out.println("bu包含2020-03-30");
        }


    }
}
