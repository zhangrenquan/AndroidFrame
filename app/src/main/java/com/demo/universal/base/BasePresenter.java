package com.demo.universal.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends IBaseV> {
    public WeakReference<V> mView;

    public void attach(V view) {
        this.mView = new WeakReference<>(view);
    }

    public void unAttach(){
        this.mView = null;
    }
}
