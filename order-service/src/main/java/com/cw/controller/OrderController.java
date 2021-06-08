package com.cw.controller;

import com.cw.feignclients.UserServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @Author 小怪兽
 * @Date 2021-06-01
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserServiceFeignClient userService;

    @GetMapping("/getUser")
    public String getUser(Integer userId) {
        return userService.getUser(userId);
    }

    @RequestMapping("/createOrder")
    public String createOrder() {
        String url = "http://user-service/getAddress";
        String addr = restTemplate.getForObject(url, String.class);
        return "order service success:" + addr;
    }

    @RequestMapping("/createOrder2")
    public String createOrder2() {
        String url = getUrl("user-service") + "/getAddress";
        String addr = restTemplate.getForObject(url,String.class);
        return "order2 service success:" + addr;
    }

    private String getUrl(String serviceName) {
        //获取服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }
        //采用负载均衡随机算法
        int size = instances.size();
        Random random = new Random();
        int num = random.nextInt(size);
        return instances.get(num).getUri().toString();
    }
}
