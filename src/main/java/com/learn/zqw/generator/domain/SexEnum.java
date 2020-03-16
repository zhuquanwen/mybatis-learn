package com.learn.zqw.generator.domain;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/16 22:29
 * @since jdk1.8
 */
public enum SexEnum {
    MALE(0, "雄性"),

    FEMALE(1, "雌性");

    public int val;
    public String label;

    public int getVal() {
        return val;
    }
    public String getLabel() {
        return label;
    }
    SexEnum(int val, String label) {
        this.val = val;
        this.label = label;
    }
}
