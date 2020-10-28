package com.kilogate.hi.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HttpClientUsage
 *
 * @author kilogate
 * @create 2020/10/27 14:20
 **/
public class HttpClientUsage {
    public static void main(String[] args) throws IOException {
        testAbort();
    }

    /**
     * 测试 get 请求
     */
    private static void testHttpGet() throws IOException {
        // http 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get 请求
        HttpGet httpGet = new HttpGet("http://www.kaops.com/");

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

    /**
     * 测试 post 请求
     */
    private static void testHttpPost() throws IOException {
        // http 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get 请求
        HttpPost httpGet = new HttpPost("http://www.kaops.com/");

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

    /**
     * 测试响应处理器
     */
    private static void testResponseHandler() throws IOException {
        // http 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // http get 请求
        HttpGet httpGet = new HttpGet("http://www.kaops.com/");

        // 响应处理器
        ResponseHandler responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                if (httpResponse == null) {
                    return null;
                }

                // 响应状态行
                StatusLine statusLine = httpResponse.getStatusLine();
                System.out.println("response status line: " + statusLine);

                // 响应内容
                HttpEntity httpEntity = httpResponse.getEntity();
                String content = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                System.out.println("response content: " + content);

                return statusLine + "\n" + content;
            }
        };

        // 执行请求获取 http 响应，由响应处理器处理响应
        Object result = httpClient.execute(httpGet, responseHandler);
        System.out.println(result);
    }

    /**
     * 测试关闭连接
     */
    private static void testClose() throws IOException {
        // http 客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // http get 请求
            HttpGet httpGet = new HttpGet("http://www.kaops.com/");

            // 执行请求获取 http 响应
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
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
    }

    /**
     * 测试中止请求
     */
    private static void testAbort() throws IOException {
        // http 客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // http get 请求
            HttpGet httpGet = new HttpGet("http://www.kaops.com/");

            // 执行请求获取 http 响应
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                if (httpResponse == null) {
                    return;
                }

                // 响应状态行
                System.out.println("response status line 1: " + httpResponse.getStatusLine());

                // 中止请求
                httpGet.abort();

                System.out.println("response status line 2: " + httpResponse.getStatusLine());
            }


            // 执行请求获取 http 响应
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                if (httpResponse == null) {
                    return;
                }

                // 响应状态行
                System.out.println("response status line 3: " + httpResponse.getStatusLine());
            }
        }
    }
}
