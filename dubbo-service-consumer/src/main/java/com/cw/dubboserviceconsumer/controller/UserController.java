package com.cw.dubboserviceconsumer.controller;

import com.cw.api.UserService;
import com.cw.entity.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小怪兽
 * @Date 2021-06-04
 */
@RestController
public class UserController {

    @DubboReference
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(String name,Integer age) {
        return userService.getUser(name,age);
    }
}
