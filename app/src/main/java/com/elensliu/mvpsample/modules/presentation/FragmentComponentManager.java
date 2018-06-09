package com.elensliu.mvpsample.modules.presentation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.ref.WeakReference;

/**
 * Created by elensliu on 2016/11/15.
 */

public class FragmentComponentManager {


    class FragmentParam {

        public Fragment from;
        public Class<?> cls;
        public Object data;
    }

    public static FragmentComponentManager setUp(FragmentActivity activity,
                                                 int fragmentContainerId) {

        FragmentComponentManager manager = new FragmentComponentManager(activity,
                fragmentContainerId);
        return manager;
    }


    private WeakReference<FragmentActivity> mActivity;

    private FragmentManager fragmentManager;
    private AbstractBaseFragment mCurrentFragment;
    private int fragmentContainerId;

    /**
     * fragment 设置是否能回退显示
     */
    private boolean backPressedable = false;


    private FragmentComponentManager(FragmentActivity activity,
                                     int fragmentContainerId) {

        mActivity = new WeakReference<>(activity);
        fragmentManager = mActivity.get()
                .getSupportFragmentManager();
        this.fragmentContainerId = fragmentContainerId;
    }

    public void setBackPressedable(boolean backPressedable) {
        this.backPressedable = backPressedable;
    }

    private String getFragmentTag(FragmentParam param) {
        StringBuilder sb = new StringBuilder(param.cls.toString());
        return sb.toString();
    }


    public void pushFragmentToBackStack(Class<? extends AbstractBaseFragment> cls,
                                        Object data) {
        FragmentParam param = new FragmentParam();
        param.cls = cls;
        param.data = data;
        goToThisFragment(param);
    }

    private void goToThisFragment(FragmentParam param) {
        Class<?> cls = param.cls;
        if (cls == null) {
            return;
        }

        try {
            String fragmentTag = getFragmentTag(param);
            AbstractBaseFragment fragment = (AbstractBaseFragment) fragmentManager.findFragmentByTag(fragmentTag);
            if (fragment == null) {
                if (mCurrentFragment != null
                        && mCurrentFragment.getClass() == cls) {
                    fragment = mCurrentFragment;
                } else {

                    fragment = (AbstractBaseFragment) cls.newInstance();

                }
            }

            if (mCurrentFragment != null && mCurrentFragment != fragment) {
                mCurrentFragment.onLeave();
            }

            fragment.onEnter(param.data);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (mCurrentFragment != null
                    && fragment != mCurrentFragment) {
                ft.remove(mCurrentFragment);
            }

            if (fragment.isAdded()) {

                ft.show(fragment);
            } else {

                ft.add(fragmentContainerId,
                        fragment,
                        fragmentTag);
            }
            mCurrentFragment = fragment;
            if (backPressedable) {
                ft.addToBackStack(fragmentTag);
            }

            ft.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void removeFragment(Class<? extends AbstractBaseFragment> cls) {
        if (fragmentManager.isDestroyed()) {

            return;
        }
        String fragmentTag = cls.toString();

        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(fragment);
            ft.commitAllowingStateLoss();
        }
    }
}
