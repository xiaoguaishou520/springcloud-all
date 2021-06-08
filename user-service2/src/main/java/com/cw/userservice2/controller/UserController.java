package com.cw.userservice2.controller;

import com.cw.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小怪兽
 * @Date 2021-06-01
 */
@RestController
public class UserController {

    @RequestMapping("/getAddress")
    public String getAddress(){
        return "8081 bj";
    }

    @GetMapping("/getUser")
    public String getUser(Integer userId) {
        if (userId == 100) {
            return new User(100,"小明-2",18).toString();
        }
        if (userId == 200) {
            return new User(200,"小红-2",19).toString();
        }
        return "没有此用户。。。";
    }
}
