package com.demo.universal.activity;

import android.os.Bundle;

import com.demo.universal.R;
import com.demo.universal.adapter.HomePagerAdapter;
import com.demo.universal.base.BaseActivity;
import com.demo.universal.base.BaseLazFragment;
import com.demo.universal.base.BasePresenter;
import com.demo.universal.enity.NavigationBean;
import com.demo.universal.fragment.HomeFragment;
import com.demo.universal.fragment.MyFragment;
import com.demo.universal.weight.HorizontalTabLayout;
import com.demo.universal.weight.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {
    @BindView(R.id.home_viewpager)
    NoScrollViewPager home_viewpager;
    @BindView(R.id.home_tablayout)
    HorizontalTabLayout home_tablayout;
    private ArrayList<BaseLazFragment> fragments;
    private ArrayList<NavigationBean> titles;
    private HomePagerAdapter homePagerAdapter;

    @Override
    protected void init() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new MyFragment());

        titles.add(new NavigationBean(getResources().getString(R.string.home)));
        titles.add(new NavigationBean(getResources().getString(R.string.my)));

        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragments, titles);
        home_viewpager.setAdapter(homePagerAdapter);
        home_tablayout.setViewPager(home_viewpager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragments.clear();
        titles.clear();
        fragments = null;
        titles = null;
        homePagerAdapter = null;
    }
}