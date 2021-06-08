package com.cw.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小怪兽
 * @Date 2021-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;
    private String name;
    private Integer age;
}
