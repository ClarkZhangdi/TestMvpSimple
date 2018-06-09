package crm.wangjin.main.presentation.interactor;

/**
 * Created by elensliu on 16/10/20.
 */

public interface IInteractorCallback<T> {

    void beforeRequest();

    void success(T data);

    void onLoadCache(T data);

    void noCache();

    void onError(String errorMsg, int errorCode);
}
