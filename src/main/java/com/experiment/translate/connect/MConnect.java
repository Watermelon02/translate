package com.experiment.translate.connect;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class MConnect {
    private static List<String> cookies;

    public static Response get(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        if (cookies != null) {
            StringBuilder cookie = new StringBuilder();
            for (String srt : cookies) {
                cookie.append(srt).append(";");
            }
            connection.setRequestProperty("Cookie", cookie.toString());
        }
        connection.setDoInput(true);
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.connect();

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        reader.lines().forEach(builder::append);
        reader.close();

        return new Response(builder.toString());
    }

    public static Response post(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        if (cookies != null) {
            StringBuilder cookie = new StringBuilder();
            for (String srt : cookies) {
                cookie.append(srt).append(";");
            }
            connection.setRequestProperty("Cookie", cookie.toString());
        }
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.connect();

        Map<String, List<String>> header = connection.getHeaderFields();
        cookies = header.get("Set-cookie");

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        reader.lines().forEach(builder::append);
        reader.close();

        return new Response(builder.toString());
    }
}

