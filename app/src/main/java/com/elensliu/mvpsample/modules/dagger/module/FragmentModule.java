package com.elensliu.mvpsample.modules.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.elensliu.mvpsample.modules.dagger.scope.ScopeFragment;

import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import dagger.Module;
import dagger.Provides;

/**
 * Created by elensliu on 16/10/18.
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule (Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @ScopeFragment
    @ScopeLife("Activity")
    public Context provideActivityContext () {
        return mFragment.getActivity ();
    }

    @Provides
    @ScopeFragment
    public Activity provideActivity () {
        return mFragment.getActivity ();
    }

    @Provides
    @ScopeFragment
    public Fragment provideFragment () {
        return mFragment;
    }
}
