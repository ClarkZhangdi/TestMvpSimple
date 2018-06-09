package com.elensliu.mvpsample.modules;

import com.elensliu.mvpsample.BuildConfig;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.executor.Executor;
import crm.wangjin.main.domain.repository.network.http.HttpManager;
import crm.wangjin.main.domain.utils.LogUtil;
import crm.wangjin.main.presentation.components.BaseApplication;

/**
 * Created by elensliu on 2017/7/24.
 */

public class MvpApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.isShow = BuildConfig.DEBUG;
        ApplicationComponent.init(this);
        Executor.initMode(Executor.MODE_LITE_ASYNC);
        HttpManager.builder()
                .create(HttpManager.MODE_RETROFIT);

    }
}
