package util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketbelongtoProvince {
    /***
     * 根据市场名字得到该市场属于那个省份
     * @param marketName
     * @return provinceName
     * 北京, 天津,河北,山西,内蒙古,
     * 辽宁,吉林, 黑龙江, 上海, 江苏, 浙江, 安徽, 福建,
     * 江西,山东,河南,湖北,湖南,广东,广西,海南,重庆,四川,
     * 贵州,云南,西藏,陕西,甘肃,青海,宁夏,新疆
     */

    private static String provinceName;
    static List<String> provinceList = new ArrayList<String>();

    public static String GetProvice(String marketName) {
        provinceList.addAll(Arrays.asList("北京", "天津", "河北", "山西", "内蒙",
                "辽宁", "吉林", "黑龙", "上海", "江苏", "浙江", "安徽", "福建",
                "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "重庆", "四川",
                "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆"));
        try {
            String name = marketName.substring(0, 2);
            if (provinceList.contains(name)) {
                provinceName = name;
            } else if (name.equals("乐亭") ||
                    name.equals("石家") ||
                    name.equals("邯郸") ||
                    name.equals("保定") ||
                    name.equals("刑台") ||
                    name.equals("唐山") ||
                    name.equals("永年") ||
                    name.equals("承德") ||
                    name.equals("张北") ||
                    name.equals("威县"))
                provinceName = "河北";
            else if (
                    name.equals("阳泉") ||
                            name.equals("孝义") ||
                            name.equals("晋城") ||
                            name.equals("太原") ||
                            name.equals("运城")
            )
                provinceName = "山西";
            else if (
                    name.equals("呼和") ||
                            name.equals("巴彦") ||
                            name.equals("通辽")
            )
                provinceName = "内蒙";
            else if (
                    name.equals("沈阳") ||
                            name.equals("大连")
            )
                provinceName = "辽宁";
            else if (
                    name.equals("长春")
            )
                provinceName = "吉林";
            else if (
                    name.equals("哈尔") ||
                            name.equals("牡丹")
            )
                provinceName = "黑龙";
            else if (
                    name.equals("徐州") ||
                            name.equals("苏州") ||
                            name.equals("南京") ||
                            name.equals("无锡") ||
                            name.equals("天惠")
            )
                provinceName = "江苏";
            else if (
                    name.equals("绍兴") ||
                            name.equals("宁波") ||
                            name.equals("温州") ||
                            name.equals("浙北") ||
                            name.equals("杭州")
            )
                provinceName = "浙江";
            else if (
                    name.equals("马鞍") ||
                            name.equals("阜阳") ||
                            name.equals("蚌埠")
            )
                provinceName = "安徽";
            else if (name.equals("厦门"))
                provinceName = "福建";
            else if (name.equals("南昌"))
                provinceName = "江西";
            else if (name.equals("青岛") ||
                    name.equals("临清") ||
                    name.equals("潍坊") ||
                    name.equals("济南") ||
                    name.equals("中国") ||
                    name.equals("鲁南") ||
                    name.equals("历城") ||
                    name.equals("滕州") ||
                    name.equals("金乡")
            )
                provinceName = "山东";
            else if (
                    name.equals("郑州") ||
                            name.equals("焦作") ||
                            name.equals("三门") ||
                            name.equals("周口")
            )
                provinceName = "河南";
            else if (
                    name.equals("武汉") ||
                            name.equals("襄阳") ||
                            name.equals("两湖")

            )
                provinceName = "湖北";
            else if (
                    name.equals("红星") ||
                            name.equals("邵阳")
            )
                provinceName = "湖南";
            else if (
                    name.equals("广州") ||
                            name.equals("韶关") ||
                            name.equals("汕头") ||
                            name.equals("佛山")
            )
                provinceName = "广东";
            else if (
                    name.equals("柳州") ||
                            name.equals("玉林")
            )
                provinceName = "广西";
            else if (
                    name.equals("海口")
            )
                provinceName = "海南";
            else if (
                    name.equals("成都") ||
                            name.equals("绵阳")
            )
                provinceName = "四川";
            else if (
                    name.equals("贵阳") ||
                            name.equals("遵义")
            )
                provinceName = "贵州";
            else if (
                    name.equals("拉萨")
            )
                provinceName = "西藏";
            else if (
                    name.equals("西安") ||
                            name.equals("西部")
            )
                provinceName = "陕西";
            else if (
                    name.equals("陇西") ||
                            name.equals("兰州")
            )
                provinceName = "甘肃";
            else if (
                    name.equals("青藏")
            )
                provinceName = "青海";
            else if (
                    name.equals("吴忠")
            )
                provinceName = "宁夏";
            else if (name.equals("乌鲁"))
                provinceName = "新疆";
            else
                provinceName = "其他";
        } catch (Exception e) {
            System.out.println("根据市场得到身份名出错,用其他代替");
            provinceName = "其他";
        }
        return provinceName;
    }

}
