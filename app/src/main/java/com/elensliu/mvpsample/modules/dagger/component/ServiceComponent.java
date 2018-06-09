package com.elensliu.mvpsample.modules.dagger.component;

import android.app.Service;
import android.content.Context;


import com.elensliu.mvpsample.modules.dagger.module.ServiceModule;
import com.elensliu.mvpsample.modules.dagger.scope.ScopeActivity;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.presentation.components.BaseApplication;
import dagger.Component;

/**
 * Created by elensliu on 16/10/18.
 */
@ScopeActivity
@Component(dependencies = ApplicationComponent.class,
        modules = ServiceModule.class)
public interface ServiceComponent {

    @ScopeLife("Service")
    Context getServiceContext();

    @ScopeLife()
    BaseApplication getApplicationContext();

    Service getService();


}
