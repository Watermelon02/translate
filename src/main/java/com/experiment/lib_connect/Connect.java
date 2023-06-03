package com.experiment.lib_connect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;


public class Connect {
    private static List<String> cookies;
    private static final Gson gson = new Gson();

    public static <T> Response<T> get(String url, Class<T> responseType) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            if (cookies != null) {
                StringBuilder cookie = new StringBuilder();
                for (String str : cookies) {
                    cookie.append(str).append(";");
                }
                connection.setRequestProperty("Cookie", cookie.toString());
            }
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            T response = gson.fromJson(builder.toString(), responseType);
            return new Response<>(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Response<T> post(Request request, Class<T> responseType) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
            if (cookies != null) {
                StringBuilder cookie = new StringBuilder();
                for (String str : cookies) {
                    cookie.append(str).append(";");
                }
                connection.setRequestProperty("Cookie", cookie.toString());
            }
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.connect();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bufferedWriter.write(request.getBody());
            bufferedWriter.close();
            List<String> header = connection.getHeaderFields().get("Set-cookie");
            cookies = header;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            T response = gson.fromJson(builder.toString(), responseType);
            return new Response<>(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
