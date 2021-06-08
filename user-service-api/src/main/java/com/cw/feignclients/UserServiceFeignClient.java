package com.cw.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author 小怪兽
 * @Date 2021-06-03
 */
@FeignClient("user-service")
public interface UserServiceFeignClient {

    @GetMapping("/getUser")
    String getUser(@RequestParam("userId") Integer userId,@RequestParam("name") String name);
}
