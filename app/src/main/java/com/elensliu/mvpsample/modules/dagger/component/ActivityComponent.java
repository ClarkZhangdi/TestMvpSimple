package com.elensliu.mvpsample.modules.dagger.component;

import android.app.Activity;
import android.content.Context;

import com.elensliu.mvpsample.modules.dagger.module.ActivityModule;
import com.elensliu.mvpsample.modules.dagger.scope.ScopeActivity;
import com.elensliu.mvpsample.modules.message.components.MessageActivity;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.presentation.components.BaseApplication;
import dagger.Component;

/**
 * Created by elensliu on 16/10/18.
 */
@ScopeActivity
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {

    @ScopeLife("Activity")
    Context getActivityContext();

    @ScopeLife()
    BaseApplication getApplicationContext();

    Activity getActivity();

    void inject(MessageActivity messageActivity);


}
