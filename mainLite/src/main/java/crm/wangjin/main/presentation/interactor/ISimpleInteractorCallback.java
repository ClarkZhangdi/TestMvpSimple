package crm.wangjin.main.presentation.interactor;

/**
 * Created by elensliu on 16/10/20.
 */

public abstract class ISimpleInteractorCallback<T> implements IInteractorCallback<T> {

    private IInteractorCallback<T> mCallback;

    public ISimpleInteractorCallback(IInteractorCallback<T> callback) {

        this.mCallback = callback;
    }


    @Override
    public void onLoadCache(T data) {

        mCallback.onLoadCache(data);
    }

    @Override
    public void noCache() {

        mCallback.noCache();
    }

    @Override
    public void onError(String errorMsg,int errorCode) {

        mCallback.onError(errorMsg,errorCode);
    }

}
