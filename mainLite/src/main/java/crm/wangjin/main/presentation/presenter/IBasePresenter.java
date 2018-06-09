package crm.wangjin.main.presentation.presenter;

import android.support.annotation.NonNull;

/**
 * Created by elensliu on 16/9/3.
 */
public interface IBasePresenter {

    void create();

    void resume();

    void pause();

    void destroy();

    void attachView (@NonNull IBaseView view);
}
