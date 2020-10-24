package com.kilogate.hi.java.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * UrlConnection 的用法
 *
 * @author kilogate
 * @create 2020/10/24 13:44
 **/
public class UrlConnectionUsage {
    public static void main(String[] args) throws IOException {
        // 配置全局 cookie 处理器，重定向时转发所有 cookie
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        URL url = new URL("http://www.baidu.com");

        Map<String, Object> nameValuePairs = new HashMap<>();
        nameValuePairs.put("a", "A");
        nameValuePairs.put("b", "B");
        nameValuePairs.put("c", "C");

        String userAgent = "";
        int redirects = -1;

        String result = doPost(url, nameValuePairs, userAgent, redirects);
        System.out.println(result);
    }

    public static String doPost(URL url, Map<String, Object> nameValuePairs, String userAgent, int redirects) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (userAgent != null) {
            connection.setRequestProperty("User-Agent", userAgent);
        }

        if (redirects >= 0) {
            // 关闭自动重定向（自动重定向总是会发送包含单词 Java 的通用用户代理字符串）
            connection.setInstanceFollowRedirects(false);
        }

        connection.setDoOutput(true);

        try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
            boolean first = true;

            for (Map.Entry<String, Object> pair : nameValuePairs.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    out.print('&');
                }

                String name = pair.getKey();
                String value = String.valueOf(pair.getValue());
                out.print(name);
                out.print('=');
                out.print(URLEncoder.encode(value, "UTF-8"));
            }
        }

        String encoding = connection.getContentEncoding();

        if (encoding == null) {
            encoding = "UTF-8";
        }

        if (redirects > 0) {
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String location = connection.getHeaderField("Location");

                if (location != null) {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }
            }
        } else if (redirects == 0) {
            throw new IOException("Too many redirects");
        }

        StringBuilder response = new StringBuilder();
        try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        } catch (IOException e) {
            InputStream err = connection.getErrorStream();

            if (err == null) {
                throw e;
            }

            try (Scanner in = new Scanner(err)) {
                response.append(in.nextLine());
                response.append("\n");
            }
        }

        return response.toString();
    }
}
