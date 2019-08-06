package com.modules.admin.feginService;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author 李非凡
 * @Description:
 *
 * @Date 2019/7/25 18:16
 * @Version 1.0
 */
@FeignClient("server-product")
public interface ProductServiceClient {

}
