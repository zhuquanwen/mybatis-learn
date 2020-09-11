package com.learn.zqw.association.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {
    private Integer id;

    private String name;

    private Integer pid;

    private Province province;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", province=" + province +
                '}';
    }
}