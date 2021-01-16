package com.demo.universal.net;

public class NetWorkFactory {
    public static volatile NetWorkFactory netWorkFactory;
    public INetWork netWork;

    public static NetWorkFactory getInstance() {
        if (netWorkFactory == null) {
            synchronized (NetWorkFactory.class) {
                if (netWorkFactory == null) {
                    netWorkFactory = new NetWorkFactory();
                }
            }
        }
        return netWorkFactory;
    }

    public INetWork getNetWork() {
        netWork = RetrofitUtils.getInstance();
        return netWork;
    }
}
