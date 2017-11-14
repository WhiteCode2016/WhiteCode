package com.whitecode;

import java.io.IOException;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ip代理服务器工具类
 * Created by White on 2017/11/13.
 */
public class ProxyUtils {
    /**
     * 校验代理IP的有效性，测试地址为：http://www.ip138.com
     * @param ip
     * @param port
     * @return
     */
    public static boolean checkVaildProxy(String ip,Integer port) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://www.ip138.com");
            // 代理服务器
            InetSocketAddress proxyAddr = new InetSocketAddress(ip,port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP,proxyAddr);
            connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setReadTimeout(4000);
            connection.setConnectTimeout(4000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                connection.disconnect();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkChineseCharacter(String str) {
        if(str.getBytes().length == str.length())
            return false;
        return true;
    }
    // 获取字符串中的中文
    public static String returnChineseCharacter(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        String chiResult = "";
        while (m.find()) {
            chiResult += m.group();
        }
        return chiResult;
    }

    // 获取字符串中的数字
    public static String returnNumber(String str) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void main(String[] args) {
        String str = "abc123ABC分钟";
        if(checkChineseCharacter(str)) {
            System.out.println(returnChineseCharacter(str));
            System.out.println(returnNumber(str));
        }

    }

}
