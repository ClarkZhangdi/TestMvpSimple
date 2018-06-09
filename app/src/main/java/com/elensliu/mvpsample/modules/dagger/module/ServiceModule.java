package com.elensliu.mvpsample.modules.dagger.module;

import android.app.Service;
import android.content.Context;


import com.elensliu.mvpsample.modules.dagger.scope.ScopeActivity;

import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import dagger.Module;
import dagger.Provides;

/**
 * Created by elensliu on 16/10/18.
 */
@Module
public class ServiceModule {

    private Service mService;

    public ServiceModule(Service service) {

        mService = service;
    }


    @Provides
    @ScopeActivity
    @ScopeLife("Service")
    public Context ProvideServiceContext() {

        return mService;

    }

    @Provides
    @ScopeActivity
    public Service ProvideService() {

        return mService;
    }
}
