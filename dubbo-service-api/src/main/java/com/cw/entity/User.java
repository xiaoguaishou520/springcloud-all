package com.cw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author 小怪兽
 * @Date 2021-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String name;
    private Integer age;
}
