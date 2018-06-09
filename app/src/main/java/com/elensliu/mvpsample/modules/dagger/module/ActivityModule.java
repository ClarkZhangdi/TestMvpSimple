package com.elensliu.mvpsample.modules.dagger.module;

import android.app.Activity;
import android.content.Context;


import com.elensliu.mvpsample.modules.dagger.scope.ScopeActivity;

import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import dagger.Module;
import dagger.Provides;

/**
 * Created by elensliu on 16/10/18.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ScopeActivity
    @ScopeLife("Activity")
    public Context ProvideActivityContext() {
        return mActivity;
    }

    @Provides
    @ScopeActivity
    public Activity ProvideActivity() {
        return mActivity;
    }
}
