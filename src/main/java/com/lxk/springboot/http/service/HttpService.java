package com.lxk.springboot.http.service;

import com.google.common.collect.Maps;
import com.lxk.springboot.http.util.HttpClientUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiXuekai on 2019/11/15
 */
@Service
public class HttpService {
    private static final Logger LOG = LoggerFactory.getLogger(HttpService.class);

    private static final int OK = 200;
    private static final String CHARSET = "utf-8";
    /**
     * 获取返回信息超时（ms）
     */
    private static final int READ_TIME_OUT = 10000;
    /**
     * 连接服务器超时（ms）
     */
    private static final int CONNECT_TIME_OUT = 10000;

    @Value("${url:http://192.168.1.136:8080/refresh}")
    private String url = "";

    @Value("${pageNoKey:pageNo}")
    private String pageNoKey = "pageNo";
    @Value("${pageNo:1}")
    private String pageNo = "1";
    @Value("${pageSizeKey:pageSize}")
    private String pageSizeKey = "pageSize";
    @Value("${pageSize:100}")
    private String pageSize = "100";


    public void httpPost() throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json, text/javascript;charset=" + CHARSET);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(READ_TIME_OUT)
                .setConnectTimeout(CONNECT_TIME_OUT)
                .build();
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("name", "请叫我大师兄"));
        nameValuePairList.add(new BasicNameValuePair("age", "18"));

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, CHARSET));
        httpPost.setConfig(requestConfig);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == OK) {
                HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity, CHARSET);
                LOG.info("请求OK，获得返回数据：" + s);
            } else {
                LOG.info("请求异常，获得返回状态码：" + statusCode);
            }
        } catch (Exception e) {
            LOG.error("http请求异常" + e.getMessage());
        }
    }

    public void httpPost2() {
        Map<String, String> map = Maps.newHashMap();
        map.put(pageNoKey, pageNo);
        map.put(pageSizeKey, pageSize);
        try {
            HttpClientUtils.HttpClientResult httpClientResult = HttpClientUtils.doPost(url, map);
            LOG.info("请求OK，获得返回数据：" + httpClientResult.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void httpGet() {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(READ_TIME_OUT)
                .setConnectTimeout(CONNECT_TIME_OUT)
                .build();
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == OK) {
                HttpEntity entity = response.getEntity();
                String s = EntityUtils.toString(entity, CHARSET);
                LOG.info("请求OK，获得返回数据：" + s);
            } else {
                LOG.info("请求异常，获得返回状态码：" + statusCode);
            }
        } catch (Exception e) {
            LOG.error("http请求异常" + e.getMessage());
        }
    }
}
