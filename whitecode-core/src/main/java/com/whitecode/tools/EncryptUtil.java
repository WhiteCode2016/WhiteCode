package com.whitecode.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 加密工具类（md5，sha，base64）
 * Created by White on 2017/9/26.
 */
public class EncryptUtil {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * MD5 加密
     * @param str 需要加密的字符串
     * @return
     */
    public static String md5(String str) {
        return encode(str, "MD5");
    }

    /**
     * SHA 加密
     * @param str 需要加密的字符串
     * @return
     */
    public static String sha(String str) {
        return encode(str, "SHA");
    }

    /**
     * @param str  需要加密的字符串
     * @param type 加密类型（MD5,SHA)
     * @return
     */
    public static String encode(String str, String type) {
        MessageDigest md = null;
        String dStr = null;
        try {
            md = MessageDigest.getInstance(type);
            md.update(str.getBytes());
            dStr = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            logger.info("加密出错");
            e.printStackTrace();
        }
        return dStr;
    }

    /**
     * 用base64算法进行加密
     * @param str 需要加密的字符串
     * @return base64加密后的结果
     */
    public static String base64(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(str.getBytes());
    }

    /**
     * 用base64算法进行解密
     * @param str 需要解密的字符串
     * @return base64解密后的结果
     * @throws IOException
     */
    public static String decodeBase64(String str) throws IOException {
        BASE64Decoder encoder = new BASE64Decoder();
        return new String(encoder.decodeBuffer(str));
    }
}
