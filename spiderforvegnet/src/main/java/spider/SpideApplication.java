package spider;

import java.io.IOException;

/**
 *
 * 爬虫程序启动类
 * 爬取多个网站:
 * "若要爬多个网站",各个网站的定时爬虫都在此启动类开始,
 * 若各个爬虫爬取的记录都写入一个文件需要控制线程
 * to do list:
 *      在 Jobschedling包下控制线程[未实现]
 * 否则： 无需控制线程,这情况下,我们在flume中可配置多个source 对应 一个 slink[?]
 */

public class SpideApplication {
    public static void main(String[] args) throws IOException {
      TimerSpiderforVegnet timerSpiderforVegnet  = new TimerSpiderforVegnet();
      timerSpiderforVegnet.start();
    }
}
