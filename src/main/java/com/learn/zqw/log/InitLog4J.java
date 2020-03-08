package com.learn.zqw.log;

import org.apache.log4j.PropertyConfigurator;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/8 10:35
 * @since jdk1.8
 */
public class InitLog4J {
    public static void init(){
        PropertyConfigurator.configure("log4j.properties");
    }
    static {
        init();
    }
}