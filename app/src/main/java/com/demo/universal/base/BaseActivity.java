package com.demo.universal.base;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.universal.util.ActivityManagerUtil;
import com.demo.universal.util.PermissionsUtils;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseV{
    public P mPresenter;

    //监听权限的接口
    private PermissionsUtils.IPermissionsResult permissionsResult = new PermissionsUtils.IPermissionsResult() {
        @Override
        public void passPermissons() {
            Toast.makeText(BaseApp.getContext(), "权限申请成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void forbitPermissons() {
            Toast.makeText(BaseApp.getContext(), "权限申请失败", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTransparentStatusBar();
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        ActivityManagerUtil.getInstance().addActivity(this);
        init();
        mPresenter = initPresenter();
        if (null!=mPresenter){
            mPresenter.attach(this);
        }
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=mPresenter){
            mPresenter.unAttach();
            mPresenter = null;
        }
        ActivityManagerUtil.getInstance().destroyeActivity(this);
    }

    /**
     * 获取权限
     */
    public void getPermission(String... permission) {
        PermissionsUtils.getInstance().chekPermissions(this, permission, permissionsResult);
    }


    /**
     * 设置透明沉浸式状态栏
     */
    private void setTransparentStatusBar() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置statusbar应用所占的屏幕扩大到全屏，但是最顶上会有背景透明的状态栏，它的文字可能会盖着你的应用的标题栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化操作
     */
    protected abstract void init();

    /**
     * 获取P层对象
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 网络请求
     */
    protected abstract void initData();

}
