package spider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HtmlParse;

import java.io.IOException;
import java.util.Random;

/***
 *
 * 首次收集数据的爬虫,只会执行一次，数据的增量由TimerSpider来每天定时爬取
 *
 */
public class CollectionSpiderforVegnet {
    private static final String URL = "http://www.vegnet.com.cn/Price";
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
    public static void simpleSpider() throws IOException {
        Document doc;
        doc = Jsoup.connect(URL).userAgent(USER_AGENTS[new Random().nextInt(41)]).get();
        Elements elements = doc.select("div.pricebuxright dd>a");

        for (Element ele : elements) {
            // long i=1 从每个品类第一页开始爬
            long i = 1;
            String href = ele.attr("href");
            String suffix = "&" + href.substring(15, ele.attr("href").length());
            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;

            Document eledoc;
            int times1 = 1;

            //请求超时40s
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
                    //找到表格且去表头成功,存数据
                    try {
                        if (!vegTable.isEmpty()) {
                            HtmlParse.tableParsing(vegTable, prdtype);
                            //存完数据切页
                            i++;
                            href = "http://www.vegnet.com.cn/Channel/Price?page=" + i + suffix;
                            ctimer = 1000*10;
                            times1 = 1;

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
                // 这里指定一个品类爬取多少页,若去掉,会在请求超时时间允许下,最大可能爬取
//                if(i>449){
//                    System.out.println("------------------------------------------" +
//                            "-------------"+ele.attr("title")+"---------------");
//                    break;
//                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CollectionSpiderforVegnet.simpleSpider();
    }
}
