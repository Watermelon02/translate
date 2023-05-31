package com.experiment.translate.helper;

import java.security.MessageDigest;
/**生成百度翻译api格式的请求*/
public class TranslateHelper {
    private static final String APP_ID = "20230531001695232";
    private  static final String SECRET_KEY = "OQomgErq7Ydl_iqxDH8k";
    private static final String SALT = "1435660288";

    private static String generateSign(String query) {
        try {
            String signStr = APP_ID + query + SALT + SECRET_KEY;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(signStr.getBytes("UTF-8"));

            StringBuilder signBuilder = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    signBuilder.append("0");
                }
                signBuilder.append(hex);
            }
            return signBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
