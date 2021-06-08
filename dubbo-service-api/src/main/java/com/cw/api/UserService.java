package com.cw.api;

import com.cw.entity.User;

public interface UserService {

    User getUser(String name,Integer age);
}
