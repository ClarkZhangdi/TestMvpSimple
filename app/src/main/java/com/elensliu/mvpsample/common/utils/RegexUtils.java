package com.elensliu.mvpsample.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <pre>
 *     desc  : 正则相关工具类
 * </pre>
 */
public class RegexUtils {

    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";

    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";


    /**
     * 正则：密码，取值范围为a-z,A-Z,0-9,必须是6-20位
     */
//    public static final String REGEX_PASSWORD = "^(?!([a-zA-Z]+|\\d+)$)[a-zA-Z\\d]{6,16}(?<!_)$";
    public static final String REGEX_PASSWORD = "^([a-zA-Z0-9]|[._@!?,:;/\\-\\+\\=\\{\\}\\(\\)|#$%^&*~]){6,16}$";
    public static final String REGEX_PASSWORD2 = "^[u4e00-u9fa5]$";


    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 正则：短信验证码 4位数字
     */
    public static final String REGEX_VERIFY_CODE = "^\\d{4}$";

    /**
     * If u want more please visit http://toutiao.com/i6231678548520731137/
     */

    /**
     * 验证手机号（简单）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMobileSimple(CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 验证手机号（精确）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMobileExact(CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }


    /**
     * 验证密码
     * <p>取值范围为a-z,A-Z,0-9,"_"，不能以"_"结尾,密码必须是6-20位</p>
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isPassword(CharSequence input) {

        boolean regex1 = isMatch(REGEX_PASSWORD, input);
        return regex1;
    }


    /**
     * 验证 4位验证码
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isVerifyCode(CharSequence input) {
        return isMatch(REGEX_VERIFY_CODE, input);
    }


    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 获取正则匹配的部分
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return 正则匹配的部分
     */
    public static List<String> getMatches(String regex, CharSequence input) {
        if (input == null) return null;
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * 获取正则匹配分组
     *
     * @param input 要分组的字符串
     * @param regex 正则表达式
     * @return 正则匹配分组
     */
    public static String[] getSplits(String input, String regex) {
        if (input == null) return null;
        return input.split(regex);
    }

    /**
     * 替换正则匹配的第一部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 替换正则匹配的第一部分
     */
    public static String getReplaceFirst(String input, String regex, String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * 替换所有正则匹配的部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 替换所有正则匹配的部分
     */
    public static String getReplaceAll(String input, String regex, String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }

    public static void main(String[] agrs) {

        boolean is = isPassword("Aaa111");
        System.out.println(is);
    }
}