package com.cw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 小怪兽
 * @Date 2021-06-08
 */
@RestController
public class SentinelController {

    @RequestMapping("/flush")
    public String flush(){
        return "success";
    }
}
