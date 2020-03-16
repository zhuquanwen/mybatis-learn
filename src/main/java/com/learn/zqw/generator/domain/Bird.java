package com.learn.zqw.generator.domain;

import lombok.Data;

@Data
public class Bird {
    private Integer id;

    private String name;

    private SexEnum sex = SexEnum.MALE;


}