package com.experiment.translate.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成百度翻译api格式的请求
 */
public class SignGenerator {
    private static final String BAIDU_APP_ID = "20230531001695232";
    private static final String BAIDU_SECRET_KEY = "OQomgErq7Ydl_iqxDH8k";
    private static final String SALT = "12345678";

    public static String generateBaiduSign(String q) {
        // 拼接字符串
        String signStr = BAIDU_APP_ID + q + SALT + BAIDU_SECRET_KEY;
        // 计算MD5
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md5.digest(signStr.getBytes());

        // 转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(b & 0xff);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }

        return sb.toString();
    }

    private static final String YOUDAO_APP_ID = "12ec819274fd65c7";
    private static final String YOUDAO_KEY = "kH6dUa85BybNQQ2nn0Jym1NkToN5vSG0";

    public static String generateYoudaoSign(String q,String curtime) {

        try {
            // 计算input
            String input;
            if (q.length() <= 20) {
                input = q;
            } else {
                String prefix = q.substring(0, 10);
                String suffix = q.substring(q.length() - 10);
                input = prefix + q.length() + suffix;
            }
            // 拼接字符串
            String signStr = YOUDAO_APP_ID + input + SALT + curtime + YOUDAO_KEY;

            // 计算SHA-256
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha256.digest(signStr.getBytes(StandardCharsets.UTF_8));

            // 转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
