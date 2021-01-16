package com.demo.universal.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseLazFragment extends Fragment {
    //是否可见
    protected Boolean isFragmentVisible = false;
    //activity是否初始化完成
    protected boolean isActivityPrepared = false;
    //是否是第一次加载
    protected boolean isDataFirstInit = true;
    protected View mRootView;
    protected Activity mActivity;

    /**
     * 在这里实现Fragment数据的缓加载.
     * 初始化和切换的时候都会调用它
     *
     * @param isFragmentVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isFragmentVisibleToUser) {
        super.setUserVisibleHint(isFragmentVisibleToUser);
        if (isFragmentVisibleToUser == true) {//当可见的时候执行操作
            isFragmentVisible = true;
            loadData();
        } else {//不可见时执行相应的操作
            isFragmentVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityPrepared = true;
        initView();
        loadData();
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void lazyLoad();//子类实现

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 懒加载
     */
    protected void loadData() {
        //当fragment可见、activity初始化完成、第一次加载时执行
        if (isActivityPrepared && isFragmentVisible && isDataFirstInit) {
            isDataFirstInit = false;
            lazyLoad();
        }
    }


    //不可见时执行相应的操作
    protected void onInvisible() {
    }
}
