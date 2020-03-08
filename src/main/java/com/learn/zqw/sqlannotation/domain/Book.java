package com.learn.zqw.sqlannotation.domain;

import lombok.Data;

/**
 * Book实体类
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/8 12:05
 * @since jdk1.8
 */
@Data
public class Book {

    private Integer id;

    private String name;

    private Integer num;

    private Library library;
}
