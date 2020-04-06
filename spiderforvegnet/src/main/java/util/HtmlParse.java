package util;
import entity.Price;
import entity.SourceData;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import timedstrategy.StrategeDesign;

/**
 * html 页面解析器,将html对象化，表格的每一行就是一条表中的记录，就是一个Java对象
 * */
public class HtmlParse {
    public static void tableParsing(Elements vegtable,String prdtype) {
        String productDate = null,
                variety = null,
                type = null,
                marketName = null,
                provinceName ="其他";
        double minPrice = 0.0,
                maxPrice =0.0,
                avgPrice =0.0;
        for(Element ele:vegtable){
            //修复 爬取字段为空的bug,如果存在任一字段为null
            try{
                productDate = ele.select("span.k_1").text();
                productDate = productDate.substring(1,productDate.length()-1);
                productDate = FormatDate.stringToData(productDate);
                variety = ele.select("span.k_2").first().text();
                type = prdtype;
                //注意市场名可能为空,就不能根据市场名得到省份名
                marketName = ele.select("span.k_3").text();
                provinceName = MarketbelongtoProvince.GetProvice(marketName);
                // 这一块可能会有异常,我们在getContent()函数存数据的时候已处理了,
                //只要一行记录出错,整个表格扔掉
                minPrice = Double.valueOf(ele.select("span.K_2").eq(1).text());
                maxPrice = Double.valueOf(ele.select("span.K_2").eq(2).text());
                avgPrice = Double.valueOf(ele.select("span.K_2").eq(3).text());
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("存数据的时候出错了");
                break;
            }
            Price price = new Price();
            price.setMinPrice(minPrice);
            price.setMaxPrice(maxPrice);
            price.setAvgPrice(avgPrice);

            SourceData sourceData = new SourceData();
            sourceData.setProvinceName(provinceName);
            sourceData.setMarketName(marketName);
            sourceData.setType(type);
            sourceData.setVariety(variety);
            sourceData.setPrice(price);
            sourceData.setProductDate(productDate);
            System.out.println(sourceData);
        }
    }

    /**
     *
     * vegnet蔬菜王定时爬虫解析
     * @param vegtable
     * @param prdtype
     * @param strategeDesign
     * @throws InterruptedException
     */
    public static int tableParsing(Elements vegtable, String prdtype, StrategeDesign strategeDesign) throws InterruptedException {

        /**
         * 记录当天爬虫爬取的记录数,若爬虫扫描了整站没有增加一条数据
         * 说明当天没有更新数据,我们应该日期记录下,[且此日期需要持久化在文件中]
         * 每次爬虫定时启动的时候,若“嗅探”到当天已经更新数，（说明前几天的数据也更新了）
         * 应该爬取 包含在“日期队列”的中的所有日期的记录,
         * 停点为 “日期栈” 栈头日期
         */
        int bkreason = 0;
        long spiderrecord = 0;
        // productDate,variety,type,marketName,minPrice,maxPrice,
        // avgPrice这些来自网页的数据都可能为空（null,""," "),先初始化一波
        String productDate = null,
                variety = null,
                type = null,
                marketName = null,
                provinceName = "其他";
        double minPrice = 0.0,
                maxPrice = 0.0,
                avgPrice = 0.0;

        for (Element ele : vegtable) {

            // 注意所有从网页解析数据的地方,都可能会有异常,
            // 我们在getContent()函数存数据的时候已处理了,
            //只要一行记录出错,整个表格扔掉
            productDate = ele.select("span.k_1").text();
            productDate = productDate.substring(1, productDate.length() - 1);
            productDate = FormatDate.stringToData(productDate);
            if (!strategeDesign.containsOneDate(productDate)) {
                bkreason = 1;
                break;
            }
            variety = ele.select("span.k_2").first().text();
            type = prdtype;
            //注意市场名可能为空,就不能根据市场名得到省份名
            marketName = ele.select("span.k_3").text();
            provinceName = MarketbelongtoProvince.GetProvice(marketName);
            minPrice = Double.valueOf(ele.select("span.K_2").eq(1).text());
            maxPrice = Double.valueOf(ele.select("span.K_2").eq(2).text());
            avgPrice = Double.valueOf(ele.select("span.K_2").eq(3).text());
            Price price = new Price();
            price.setMinPrice(minPrice);
            price.setMaxPrice(maxPrice);
            price.setAvgPrice(avgPrice);
            SourceData sourceData = new SourceData();
            sourceData.setProvinceName(provinceName);
            sourceData.setMarketName(marketName);
            sourceData.setType(type);
            sourceData.setVariety(variety);
            sourceData.setPrice(price);
            sourceData.setProductDate(productDate);
            System.out.println(sourceData);
        }
        return bkreason;

    }

}