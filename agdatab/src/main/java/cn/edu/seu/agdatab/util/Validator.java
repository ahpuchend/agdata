package cn.edu.seu.agdatab.util;

import java.util.regex.Pattern;
/**
 * 用户注册提交信息验证
 *  来源：https://my.oschina.net/AaronDMC/blog/848408
 *  密码校验来源：https://cloud.tencent.com/developer/article/1054252
 *  1，不能全部是数字
 *  2，不能全部是字母
 *  3，必须是数字或字母
 */
public class Validator {
    //验证用户名
    /**
     * 大小写字母 4-17 位
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{4,17}$";
    //验证密码
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    //验证手机号
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    //验证邮箱
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]" +
            "@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //验证汉字
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
    //验证身份证
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
    //验证URL
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    //验证IP地址
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    /**
     * 校验用户名
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
    /**
     * 校验密码
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
    /**
     * 校验邮箱
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    /**
     * 校验汉字
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
    /**
     * 校验身份证
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
    /**
     * 校验URL
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
    /**
     * 校验IP地址
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
//    public static void main(String[] args) {
//        String username = "userRoot";
//        String pwd ="userRoot123";
//        System.out.println(Validator.isUsername(username));
//        System.out.println(Validator.isPassword(pwd));
//    }
}
