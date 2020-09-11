package com.learn.zqw.association.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/9/6 20:59
 * @since jdk1.8
 */
@Getter
@Setter
public class MyProvince {
    private Integer provinceId;

    private String provinceName;

    private List<City> cityList;

    @Override
    public String toString() {
        return "MyProvince{" +
                "provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
