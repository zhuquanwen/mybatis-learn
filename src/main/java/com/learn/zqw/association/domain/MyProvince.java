package com.learn.zqw.association.domain;

import lombok.Data;

import java.util.List;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/9/6 20:59
 * @since jdk1.8
 */
@Data
public class MyProvince {
    private Integer provinceId;

    private String provinceName;

    private List<City> cityList;
}
