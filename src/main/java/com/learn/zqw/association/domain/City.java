package com.learn.zqw.association.domain;

import lombok.Data;

@Data
public class City {
    private Integer id;

    private String name;

    private Integer pid;

    private Province province;

}