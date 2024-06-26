package com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey; // OpenAI API的密钥

    private RestTemplate restTemplate; // 用于发送HTTP请求的RestTemplate对象

    // 初始化RestTemplate对象，设置代理服务器
    public OpenAiService(@Value("${proxy.host}") String proxyHost, @Value("${proxy.port}") int proxyPort) {
        HttpHost proxy = new HttpHost(proxyHost, proxyPort);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRoutePlanner(routePlanner)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        this.restTemplate = new RestTemplate(factory);
    }

    // 根据用户数据生成简历建议
    public String generateResumeSuggestions(String userData) {
        String apiUrl = "https://api.openai.com/v1/completions"; // OpenAI API的URL

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey); // 设置请求头，包含API密钥
        headers.set("Content-Type", "application/json"); // 设置请求头，内容类型为JSON

        // 提示信息，用于生成简历建议
        String prompt = "Based on the user's career goals and current market trends, provide personalized resume suggestions for the following user data: " + userData;

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("model", "gpt-3.5-turbo"); // 使用的模型
        requestBodyMap.put("prompt", prompt); // 提示信息
        requestBodyMap.put("max_tokens", 200); // 最大生成的令牌数
        requestBodyMap.put("temperature", 0.7); // 控制输出的随机性

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(requestBodyMap); // 将请求体转换为JSON字符串
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage(); // 如果出现错误，返回错误信息
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers); // 创建HTTP实体，包含请求体和请求头

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class); // 发送POST请求，获取响应
            return response.getBody(); // 返回响应体
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage(); // 如果出现错误，返回错误信息
        }
    }
}