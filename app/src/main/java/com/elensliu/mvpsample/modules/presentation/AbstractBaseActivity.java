package com.elensliu.mvpsample.modules.presentation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;

import com.elensliu.mvpsample.modules.dagger.component.ActivityComponent;
import com.elensliu.mvpsample.modules.dagger.component.DaggerActivityComponent;
import com.elensliu.mvpsample.modules.dagger.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.domain.utils.ToastUtil;
import crm.wangjin.main.presentation.components.BaseActivity;

/**
 * Created by elensliu on 16/3/11.
 */
public abstract class AbstractBaseActivity extends BaseActivity {


    @Inject
    @ScopeLife("Activity")
    protected Context mContext;

    private ActivityComponent mActivityComponent;

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    /**
     * 管理fragment生命周期
     * 需要用到的时候初始化
     */
    protected FragmentComponentManager fragmentComponentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setBackground();
        setContentView(getLayoutID());
        initActivityComponent();
        initInjector();
        initPresenter();
        ButterKnife.bind(this);
        onPresenterCreate();
    }

    private void setBackground() {
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        contentFrameLayout.setBackgroundColor(Color.RED);

    }


    @Override
    protected void onResume() {
        super.onResume();
        onPresenterResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPresenterPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onPresenterDestroy();
    }


    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(ApplicationComponent.getInstance())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected abstract int getLayoutID();

    protected abstract void initInjector();


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {
        ToastUtil.show(message);
    }

}
