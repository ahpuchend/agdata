package timedstrategy;
import java.util.*;

/**
 * 我们的爬虫每天都会定时"嗅探"数据源网站,如果当天没有爬到更新数据,就将当天日期
 * 放入 dateQueue 队列中,可解决StrategeDesign接口中提到的情景一 和二
 */

public class IntervalStrategy implements StrategeDesign {
    //日期容器

    private LinkedList<String> dateQueue = new LinkedList<String>();

    public void addDateRecord(String datatime) {
       dateQueue.addLast(datatime);
    }

    public String getDateRecord() {
        return dateQueue.getFirst();
    }

    public Boolean containsOneDate(String stringdate){
        boolean isContains = false;
        if(dateQueue.contains(stringdate))
            isContains = true;
        return isContains;
    }

    public LinkedList<String> getAll(){
        return dateQueue;
    }


    @Override
    public String toString() {
        for (String d:dateQueue) {
            System.out.println(d);
        }
        return "";
    }
}
