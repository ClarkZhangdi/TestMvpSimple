package com.elensliu.mvpsample.modules.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;


import com.elensliu.mvpsample.modules.dagger.component.DaggerFragmentComponent;
import com.elensliu.mvpsample.modules.dagger.component.FragmentComponent;
import com.elensliu.mvpsample.modules.dagger.module.FragmentModule;

import java.util.ArrayList;
import java.util.List;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.presentation.presenter.IBasePresenter;


/**
 * Created by elensliu on 16/3/15.
 */
public abstract class AbstractBaseDialogFragment extends DialogFragment implements IFragment {

    private final String TAG = this.getClass()
            .toString();


    protected abstract void initInjector();

    protected abstract void initPresenter();


    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    protected FragmentComponent mFragmentComponent;
    private List<IBasePresenter> mPresenters;

    protected Object mDataIn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(ApplicationComponent.getInstance())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
        initPresenter();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


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
    public void onResume() {
        super.onResume();

        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.resume();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.pause();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.destroy();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }


    @Override
    public void onEnter(Object data) {
        mDataIn = data;


    }

    @Override
    public void onLeave() {

    }

    @Override
    public void onBackWithData(Object data) {


    }

    @Override
    public void onBack() {


    }

    public void addPresenter(IBasePresenter mPresenter) {

        if (mPresenters == null) {
            mPresenters = new ArrayList<>();
        }
        mPresenters.add(mPresenter);
    }
}
