package com.elensliu.mvpsample.modules.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.elensliu.mvpsample.modules.dagger.component.DaggerFragmentComponent;
import com.elensliu.mvpsample.modules.dagger.component.FragmentComponent;
import com.elensliu.mvpsample.modules.dagger.module.FragmentModule;

import butterknife.ButterKnife;
import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.utils.ToastUtil;
import crm.wangjin.main.presentation.components.BaseFragment;


/**
 * Created by elensliu on 16/3/15.
 */
public abstract class AbstractBaseFragment
        extends BaseFragment
        implements IFragment {


    protected abstract int getLayoutid();

    protected abstract void initInjector();


    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    protected FragmentComponent mFragmentComponent;
    private View mFragmentView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutid(),
                    container,
                    false);
            ButterKnife.bind(this,
                    mFragmentView);
        }

        return mFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        onPresenterCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        onPresenterResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        onPresenterPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onPresenterDestroy();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


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
