package com.demo.universal.net;

import java.util.HashMap;

public interface INetWork {
    //get
    //post
    <T> void get(String url, INetCallBack<T> netCallBack);
    <T> void get(String url, HashMap<String, String> hashMap, INetCallBack<T> netCallBack);

    <T> void post(String url, INetCallBack<T> netCallBack);
    <T> void post(String url, HashMap<String, String> hashMap, INetCallBack<T> netCallBack);
}
