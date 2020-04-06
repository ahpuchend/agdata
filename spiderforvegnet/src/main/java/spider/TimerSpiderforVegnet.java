package spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.awt.image.ImageWatched;
import timedstrategy.IntervalStrategy;
import timedstrategy.StrategeDesign;
import util.FormatDate;
import util.HtmlParse;
import util.TimerRecord;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

/**
 *
 *  <b>每天<b/>的定时爬虫[定时在azkaban中做或配linux脚本]
 *  <b>由于蔬菜网http://www.vegnet.com.cn/ 每隔几天发布数据</b>
 * 定时嗅探当天是否有数据更新，若有,则说明 日期栈中的所有数据已经放出[可能有些日期确实没有数据]
 * 步骤如下：
 *       1. 取出“日期栈”中所有保留日期;
 *  *    2. 扫描数据源，若网页日期productDate 包含在日志栈中，全部抓取
 *  *    3. 扫描停止点： 当网页最后一个品类出现一条记录的日期不在我们的“日期栈”中,结束此次爬虫
 *
 */

public class TimerSpiderforVegnet {
    private static final String[] USER_AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.18362", // Edge浏览器 版本44.18362.1.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko", // IE浏览器 版本11.175.18362.0
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x64 版本68.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x86 版本68.0
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x64 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36", // Google浏览器 x86 版本72.0.3626.121
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x86 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36", // 2345加速浏览器 版本9.9.0.19250
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36", // 2345加速浏览器 版本10.0.0.19291
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36 2345Explorer/10.0.0.19291", // 2345加速浏览器 版本10.0.0.19291
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3708.400 QQBrowser/10.4.3620.400", // QQ浏览器 版本10.4.2(3620)
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6823.400 QQBrowser/10.3.3117.400", // QQ浏览器 版本10.3.2(3117)
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3704.400 QQBrowser/10.4.3587.400", // QQ浏览器 版本10.4.2(3587)
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36", // 360浏览器 版本10.0.1920.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360SE", // 360浏览器 版本10.0.1920.0 无痕
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360EE", // 360极速浏览器 版本9.5.0.138
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36", // 360极速浏览器 版本11.0.2140.0
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4098.3 Safari/537.36", // UC浏览器 版本6.2.4098.3
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0", // 搜狗高速浏览器 版本8.5.10.30358
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36", // 小智双核浏览器 版本2.0.1.12
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER", // 猎豹安全浏览器 版本6.5.115.19331.8001
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)", // IE浏览器 x86 版本8.0.7601.17514
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)", // IE浏览器 x64 版本8.0.7601.17514
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)", // IE浏览器 x86 版本9.0.8112.16421
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)", // IE浏览器 x64 版本9.0.8112.16421
            "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko", // IE浏览器 版本11.0.9600.17843
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x64 版本68.0
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:68.0) Gecko/20100101 Firefox/68.0", // 火狐浏览器 x86 版本68.0
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x64 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36", // Google浏览器 x86 版本72.0.3626.121
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // Google浏览器 x86 版本75.0.3770.100
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.7.0.18838", // 2345加速浏览器 版本9.7.0.18838
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3947.100 Safari/537.36", // 2345加速浏览器 版本10.0.0.19291
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3708.400 QQBrowser/10.4.3620.400", // QQ浏览器 版本10.4.2(3620)
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36", // QQ浏览器 版本10.4.2(3620)
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36", // 360浏览器 版本10.0.1840.0
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4098.3 Safari/537.36", // UC浏览器 版本6.2.4098.3
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0", // 搜狗高速浏览器 版本8.5.10.30358
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36", // 小智双核浏览器 版本2.0.1.12
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36 LBBROWSER", // 猎豹安全浏览器 版本6.5.115.19331.8001
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36 OPR/62.0.3331.18", // Opera浏览器 版本62.0.3331.18
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36 OPR/62.0.3331.72", // Opera浏览器 版本62.0.3331.72
    };
    private static String productDate = null;

    //爬虫id
    private UUID spiderId = UUID.randomUUID();
    //爬虫name
    private String spiderName = "vegnetTimeSpider";
    //得到当前系统时间
    static String  nowTime = FormatDate.getSystemCurrentTime(new Date());
    private static final String URL = "http://www.vegnet.com.cn/Price";
    private final static int  REQUEST_TIME = 1000*20;

    /**
     * 嗅探器：
     *  嗅探 http://www.vegnet.com.cn/Channel/Price?page=1&flag=12&ename=fanqie 第一个日期
     */

    public static void sniffing(){
        //嗅探番茄首页
        Document eledoc;
        final String TIME_URL = "http://www.vegnet.com.cn/Channel/Price?page=1&flag=12&ename=fanqie";
        int trytimes = 1;
        while(true){
            try{
                System.out.println("开始嗅探的网址为: " + TIME_URL + " 请求超时为: " + REQUEST_TIME);
                Connection con = Jsoup.connect(TIME_URL).userAgent(
                        USER_AGENTS[new Random().nextInt(41)]).timeout(REQUEST_TIME);
                eledoc = con.get();
                System.out.println("网页请求成功success,开始嗅探...");
                try{
                    //得到网页最后一次的更新时间
                    String lastedTime = eledoc.select("div.jxs_list>p").eq(1)
                            .first().select("span.k_1")
                            .text();
                    lastedTime = lastedTime.substring(1,lastedTime.length()-1);
//                    System.out.println(lastedTime);
                    //嗅探日期
                    productDate = lastedTime;
                    System.out.println("嗅探成功success,已得到网页最后一次更新时间");
                    break;
                }catch (Exception getLastedTime){
                    System.out.println("嗅探网页最后一次更新时间失败failed");
                    getLastedTime.printStackTrace();
                }
            }catch (Exception failsniff){
                System.out.println(failsniff.getMessage());
                failsniff.printStackTrace();
            }
        }
    }

    /**
     * 控制器:
     *   判断网页是否已更新，
     *   若更新(嗅探的日期在日期队列中), 取“日期栈”,抓数据
     *   若未更新, 存日期进入“日期栈”,结束定时爬虫
     */

    public static void controller() throws IOException {
        System.out.println("网页端日期为: "+ productDate);
        //格式化网页端日期为yyyy-mm-dd
        productDate = FormatDate.stringToData(productDate);
        StrategeDesign strategeDesign = new IntervalStrategy();
        // 先把当前日期加入内存的日期队列
        strategeDesign.addDateRecord(nowTime); //等价于strategeDesign.addDateRecord(nowTime);
        //拿到文件中的日期队列
        TimerRecord.getAllDateRecord(strategeDesign);

        if(strategeDesign.containsOneDate(productDate)){
            //说明当天更新了
            System.out.println("数据更新了,存更新数据中........");
            //存更新的数据
            service(strategeDesign);
        }else{

            System.out.println("数据还没更新,停更在"+productDate);
            System.out.println("文件日期队列信息情况更新结束");
            //nowTime 入内存"日期队列",再将内存日期队列重新写入文件
            try{
                LinkedList<String> allrecord = strategeDesign.getAll();
                for (String stringdate: allrecord){
                    TimerRecord.writeDateRecord(stringdate);
                }
            }catch (Exception writedatefail){
                System.out.println(writedatefail.getMessage());
                System.out.println("日期队列存文件fail");
                System.out.println("一段时间的数据可能会丢掉");
            }
        }
    }

    /**
     * 服务器：
     *   用到了服务器，说明网站数据更新了
     *   我们需要使用 接口 StrategeDesign 出处理各种更新情况
     * * 存更新的数据,
     * * 针对vegnet蔬菜网 全品类扫描
     * * 若 网页上的时间 在 “日期队列”中,即为更新数据
     * * 品类切换点： 当且仅当 网页日期不在 “日期队列”中，立即推：已经爬到原始数据,换品类
     * * 爬虫结束（停止）点： 最后一个品类出现 切换点。
     */

    public static void service(StrategeDesign strategeDesign) throws IOException {

        Document doc;
        Elements elements;
        //这个是网站的首页,这都进不去,异常处理也没啥意思.....
        doc = Jsoup.connect(URL).userAgent(USER_AGENTS[new Random().nextInt(41)]).get();

        /**
         * 和CollectionSpiderforVegnet 里面simpleSpider函数功能类似,多加了一个日期判断
         */

        elements = doc.select("div.pricebuxright dd>a");

        for (Element ele : elements) {
            long i = 1;
            String href = ele.attr("href");
            String suffix = "&" + href.substring(15, ele.attr("href").length());
            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;

            Document eledoc;
            int times1 = 1;

            //请求超时10s
            int ctimer = 1000*10;
            int chanpage = 1;
            while (true) {
                try {
                    System.out.println("开始爬的网址为: " + href + " 请求超时为: " + ctimer);
                    Connection con = Jsoup.connect(href).userAgent(
                            USER_AGENTS[new Random().nextInt(41)]).timeout(ctimer);
                    eledoc = con.get();
                    //页面进入success
                    Elements vegTable;
                    String prdtype = ele.attr("title");
                    //找表格
                    try {
                        System.out.println("开始爬表格......");
                        vegTable = eledoc.select("div.jxs_list>p");
                        if (vegTable.isEmpty())
                            throw new NullPointerException();
                    } catch (Exception notable) {
                        System.out.println(notable.getMessage());
                        System.out.println("出错了,找不到表格,切页");
                        //进入下一页
                        i++;
                        if (chanpage < 2) {
                            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;
                            ctimer = 1000*10;
                            times1 = 1;
                            ++chanpage;
                            continue;
                        } else {
                            //连续两页都找不到表格,换品类
                            break;
                        }
                    }

                    //找到表格,去表头
                    try {
                        System.out.println("开始去表头......");
                        vegTable.remove(0);
                    } catch (Exception nodata) {
                        System.out.println(nodata.getMessage());
                        System.out.println("出错了,去表格头出现异常,切页");
                        i++;
                        if (chanpage < 2) {
                            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;
                            ctimer = 1000*10;
                            times1 = 1;
                            ++chanpage;
                            continue;
                        } else {
                            break;
                        }
                    }
                    //找到表格且去表头成功,存数据,这里要加入日期判断,是更新的数据才存
                    try {
                        if (!vegTable.isEmpty()) {
                            int bkson =  HtmlParse.tableParsing(vegTable, prdtype,strategeDesign);
                            if(bkson == 1){
                                System.out.println("该品类当日更新一爬完,换品类");
                                break;
                            }else{
                                //存完数据切页
                                i++;
                                href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;
                                ctimer = 1000*10;
                                times1 = 1;
                            }
                        } else {
                            // 去了表头后,没数据换品类
                            break;
                        }
                    } catch (Exception storedata) {
                        System.out.println("出错了,存数据出现异常,切页");
                        i++;
                        if (chanpage < 2) {
                            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;
                            ctimer = 1000*10;
                            times1 = 1;
                            chanpage++;
                        } else {
                            break;
                        }
                    }

                } catch (Exception e) {
                    if (times1 < 3) {
                        System.out.println("第" + times1 + "次尝试,该页进不去");
                        times1++;
                        //每次尝试比上次请求时间多60s,等一会
//                            Thread.sleep(1000 * 60);
                        ctimer = ctimer + 10000;
                    } else {
                        System.out.println("尝试了3次,该页都经不去,切页");
                        //先等1min在切页
//                            Thread.sleep(1000 * 60);
                        //切页
                        i++;
                        System.out.println("(chanpage :" + chanpage);
                        if (chanpage < 2) {
                            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;
                            ctimer = 1000*10;
                            times1 = 1;
                            ++chanpage;
                        } else {
                            //换了2页都不行,换品类
                            System.out.println("连续2页进不去,换品类");
                            break;
                        }
                    }
                }
            }
        }
    }
    /**
     * 定时爬虫启动
     */
    public static void start() throws IOException {
        sniffing();
        controller();
    }

}
