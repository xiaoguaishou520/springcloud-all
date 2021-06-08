package com.cw.dubboserviceprovider.service;

import com.cw.api.UserService;
import com.cw.entity.User;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author 小怪兽
 * @Date 2021-06-04
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(String name, Integer age) {
        return new User(name,age);
    }
}
