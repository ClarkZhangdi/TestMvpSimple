package com.elensliu.mvpsample.modules.presentation;

import android.app.Service;


import com.elensliu.mvpsample.modules.dagger.component.DaggerServiceComponent;
import com.elensliu.mvpsample.modules.dagger.component.ServiceComponent;
import com.elensliu.mvpsample.modules.dagger.module.ServiceModule;

import java.util.ArrayList;
import java.util.List;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.presentation.presenter.IBasePresenter;

/**
 * Created by elensliu on 16/3/11.
 */
public abstract class AbstractBaseService extends Service {

    private final String TAG = this.getClass()
            .toString();


    private ServiceComponent serviceComponent;

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    private List<IBasePresenter> mPresenters;

    public void addPresenter(IBasePresenter mPresenter) {


        if (mPresenters == null) {
            mPresenters = new ArrayList<>();
        }
        mPresenters.add(mPresenter);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initServiceComponent();
        initInjector();
        initPresenter();

        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.create();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.destroy();
                }
            }
        }
    }

    private void initServiceComponent() {

        serviceComponent = DaggerServiceComponent.builder().applicationComponent(ApplicationComponent
                .getInstance())
                .serviceModule(new ServiceModule(this))
                .build();

    }


    protected abstract void initInjector();

    protected abstract void initPresenter();
}
