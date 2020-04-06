package timedstrategy;

import java.util.LinkedList;

/**
 * 此类接口为定时爬虫的爬取策略
 * 爬取的策略是由数据源网站的发布数据的策略指定的,有如下情景
 * 情景一： 数据源网站每天都会定时发布数据
 * 情景二： 数据源网站每隔几天定时发布数据
 * 情景三： 数据源网站断断续续实时发布数据 // [ 这个我们无法解决 ]
 *此接口供情景一 二解决方案
 *
 */
public interface StrategeDesign {
    public void addDateRecord(String datetime);
    public String getDateRecord();
    public Boolean containsOneDate(String stringdate);
    public LinkedList<String> getAll();
 }
