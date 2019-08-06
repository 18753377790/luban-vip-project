package com.luban.util;

import com.luban.transaction.TransactionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 李非凡
 * @Description:
 * HTTP工具类
 * 用于发送HTTP请求
 * @Date 2019/7/28 9:27
 * @Version 1.0
 */
@Component
public class HttpUtil {

    /**
     * Rest模板
     */
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * 发送HTTP的post请求
     * @param url
     * @return
     */
    public static Object post(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.set("groupId", TransactionManager.getCurrent());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity(null, headers);
        return restTemplate.postForObject(url, httpEntity, Object.class);
    }
}
