package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FormatDate;
import java.util.Date;

public class SourceData {
    //省份名字
    String provinceName;
    //市场名
    String marketName;
    //品类
    String type;
    //品种
    String variety;
    //价格类
    Price price = new Price();
    //农产品产品网页发布日期
    String productDate;
    //爬虫抓取记录日期"yyyy-mm-dd"
    String fetchDate = FormatDate.getSystemCurrentTime(new Date());

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    @Override
    public String toString() {
        Logger logger = LoggerFactory.getLogger(SourceData.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(provinceName)
                .append("\t").append(marketName)
                .append("\t").append(type)
                .append("\t").append(variety)
                .append("\t").append(price.getMinPrice())
                .append("\t").append(price.getMaxPrice())
                .append("\t").append(price.getAvgPrice())
                .append("\t").append(productDate)
                .append("\t").append(fetchDate);
        logger.info(stringBuilder.toString());
        return " ";
    }
}

