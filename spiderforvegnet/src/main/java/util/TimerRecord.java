package util;

import timedstrategy.StrategeDesign;
import java.io.*;

/**
 * 定时器每次运行的记录 保存在 /resources/daterecord.txt,
 * 每次确定需要网站已经更新了,取出文件中所有的“日期”作为“日期栈”------称为恢复现场
 * 记录很少，持久化用文件就可以
 * to do list:
 * 若要爬取多个站，可考虑使用 redis,mysql 做存储,有时间在做^^
 *
 */
public class TimerRecord {
    //writeDateRecord() 是每次加一条“yyyy-mm-dd”
    public static void writeDateRecord(String stringdate) throws IOException {
        try{
            String pathname = "src/main/resources/daterecord.txt";
            File file = new File(pathname);
            FileOutputStream fos = null;
            if(!file.exists()){
                //如果文件不存在，就创建该文件
                file.createNewFile();
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                //这里构造方法多了一个参数true,表示在文件末尾追加写入
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter buff= new BufferedWriter(fw);
                buff.write(stringdate);
                buff.newLine();
                buff.close();
                fw.close();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    // 一次性得到所有的日期,在把文件内容置空
    public static void getAllDateRecord(StrategeDesign strategeDesign){
        try {
            //文件相对地址
            String pathname = "src/main/resources/daterecord.txt";
            //读取以上路径的daterecord.txt文件
            File file = new File(pathname);
            //建立一个输入流对象reader
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
//            StrategeDesign strategeDesign = new IntervalStrategy();
            while (line != null) {
                strategeDesign.addDateRecord(line);
                line = br.readLine();
            }
            System.out.println(strategeDesign.toString());
            //文件置空
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws IOException {
//        timerrecord.writeDateRecord("2020-03-27");
//        timerrecord.writeDateRecord("2020-03-28");
//        timerrecord.writeDateRecord("2020-03-29");
//        timerrecord.getAllDateRecord();
//    }
}



