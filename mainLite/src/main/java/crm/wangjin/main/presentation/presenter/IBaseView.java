package crm.wangjin.main.presentation.presenter;

/**
 * Created by elensliu on 16/3/14.
 */
public interface IBaseView {

    void showProgress();

    void hideProgress();

    void showMsg (String message);
}
