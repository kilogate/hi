package com.kilogate.hi.util;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HttpClientUtil
 *
 * @author kilogate
 * @create 2020/10/27 14:20
 **/
public class HttpClientUtil {
    public static void main(String[] args) throws IOException {
        testHttpGet();
    }

    private static void testHttpGet() throws IOException {
        // http 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get 请求
        HttpGet httpGet = new HttpGet("http://nc.hz.service.163.org/playlist/open/nc/playlist/detail?pid=SP1561815733840");

        // 执行请求获取 http 响应
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        if (httpResponse == null) {
            return;
        }

        // 响应状态行
        StatusLine statusLine = httpResponse.getStatusLine();
        System.out.println("response status line: " + statusLine);

        // 响应内容
        HttpEntity httpEntity = httpResponse.getEntity();
        String content = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        System.out.println("response content: " + content);
    }
}
