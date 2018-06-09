package crm.wangjin.main.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import crm.wangjin.main.domain.executor.Executor;
import crm.wangjin.main.domain.repository.network.http.HttpManager;
import crm.wangjin.main.domain.utils.LogUtil;
import crm.wangjin.main.domain.utils.RxBus;
import crm.wangjin.main.domain.utils.ToastUtil;
import crm.wangjin.main.event.NetworkChangeEvent;
import crm.wangjin.main.presentation.interactor.IInteractorCallback;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by elensliu on 16/9/3.
 */
public abstract class AbsBasePresenter<T extends IBaseView, E>
        implements IBasePresenter,
        IInteractorCallback<E> {


    private T mView;
    private List<String> taskTasks;
    private Subscription mSubscription;


    public T getBaseView() {

        return mView;
    }


    public void addTaskTag(String tag) {

        if (taskTasks == null) {
            taskTasks = new ArrayList<>();
        }
        taskTasks.add(tag);
    }

    @Override
    public void success(E data) {

    }

    @Override
    public void onLoadCache(E data) {

    }

    @Override
    public void noCache() {

    }


    @Override
    public void onError(String errorMsg,
                        int errorCode) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }

    @Override
    public void create() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

        LogUtil.w("AbsBasePresenter destroy");
        cancelTask();
        RxBus.cancelSubscription(mSubscription);


    }

    protected void cancelTask() {
        if (taskTasks != null
                && !taskTasks.isEmpty()) {

            for (String tag : taskTasks) {

                Executor.cancelByTag(tag);
                HttpManager.getClient().cancelByTag(tag);
            }
        }
    }


    @Override
    public void attachView(@NonNull IBaseView view) {

        mView = (T) view;


        mSubscription = RxBus.getInstance()
                .toObservable(NetworkChangeEvent.class)
                .subscribe(new Action1<NetworkChangeEvent>() {
                    @Override
                    public void call(NetworkChangeEvent networkChangeEvent) {

                        if (!networkChangeEvent.isConnect()) {
                            mView.hideProgress();
                            onNetworkClose();
                        }
                    }
                });
    }

    protected void onNetworkClose() {

        ToastUtil.show("网络不可用", 200);
    }



}
