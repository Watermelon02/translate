package com.experiment.translate.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成百度翻译api格式的请求
 */
public class TranslateHelper {
    private static final String APP_ID = "20230531001695232";
    private static final String SECRET_KEY = "OQomgErq7Ydl_iqxDH8k";
    private static final String SALT = "12345678";

    public static String generateSign(String q) {
        // 拼接字符串
        String signStr = APP_ID + q + SALT + SECRET_KEY;
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
}
