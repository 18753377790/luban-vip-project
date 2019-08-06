package com.luban.provider;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author 李非凡
 * @Description:
 * 回退机制
 * @Date 2019/7/23 18:00
 * @Version 1.0
 */
@Component
public class FallBackProvider implements FallbackProvider {

    /**
     * 制定为哪个微服务提供回退（这里写微服务名写*代表所有微服务）
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 降级回退的业务逻辑
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT, cause);
        }else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR, cause);
        }
    }

    /**
     * 自定义私有响应方法
     * @param status
     * @param cause
     * @return
     */
    private ClientHttpResponse response(final HttpStatus status, Throwable cause){
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(cause.getMessage().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
