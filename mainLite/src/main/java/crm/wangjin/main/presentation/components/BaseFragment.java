package crm.wangjin.main.presentation.components;

import android.support.v4.app.Fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import crm.wangjin.main.domain.utils.LogUtil;
import crm.wangjin.main.presentation.presenter.AbsBasePresenter;
import crm.wangjin.main.presentation.presenter.IBasePresenter;
import crm.wangjin.main.presentation.presenter.IBaseView;

/**
 * Created by elensliu on 2017/7/23.
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    private final String TAG = this.getClass()
            .toString();


    private List<IBasePresenter> mPresenters;

    public void addPresenter(AbsBasePresenter mPresenter) {

        if (mPresenters == null) {
            mPresenters = new ArrayList<>();
        }
        mPresenters.add(mPresenter);
    }

    protected void onPresenterCreate() {
        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.create();
                }
            }
        }
    }

    protected void onPresenterResume() {
        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.resume();
                }
            }
        }
    }

    protected void onPresenterPause() {
        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.pause();
                }
            }
        }
    }

    protected void onPresenterDestroy() {
        if (mPresenters != null) {
            for (int i = 0, z = mPresenters.size(); i < z; i++) {

                IBasePresenter presenter = mPresenters.get(i);
                if (presenter != null) {
                    presenter.destroy();
                }
            }
        }
    }

    protected void initPresenter() {

        Field[] field = this.getClass().getDeclaredFields();
        for (Field f : field) {
            LogUtil.w("Field Name = " + f.getName());
            f.setAccessible(true);
            try {
                Object p = f.get(this);
                if (p != null && p instanceof AbsBasePresenter) {
                    AbsBasePresenter presenter = (AbsBasePresenter) p;
                    presenter.attachView(this);
                    addPresenter(presenter);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
