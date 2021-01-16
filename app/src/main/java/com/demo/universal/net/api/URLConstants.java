package com.demo.universal.net.api;

public class URLConstants {
    public static String BASE_URL = "http://apis.juhe.cn/";

    /**
     * 天气
     * 接口地址：http://apis.juhe.cn/simpleWeather/query
     * 返回格式：json
     * 请求方式：http get/post
     * 请求示例：http://apis.juhe.cn/simpleWeather/query?city=%E8%8B%8F%E5%B7%9E&key=
     * 接口备注：通过城市名称或城市ID查询天气预报情况
     * url：https://www.juhe.cn/docs/api/id/73
     */
    public static String WEATHER = "simpleWeather/query";
}
