package com.elensliu.mvpsample.modules.dagger.component;

import android.app.Activity;
import android.content.Context;


import com.elensliu.mvpsample.modules.dagger.module.FragmentModule;
import com.elensliu.mvpsample.modules.dagger.scope.ScopeFragment;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.presentation.components.BaseApplication;
import dagger.Component;

/**
 * Created by elensliu on 16/10/18.
 */
@ScopeFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    @ScopeLife("Activity")
    Context getActivityContext();

    @ScopeLife("Application")
    BaseApplication getApplicationContext();

    Activity getActivity();

}
