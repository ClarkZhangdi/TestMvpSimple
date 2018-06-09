package com.elensliu.mvpsample.modules.message.interactors;

import crm.wangjin.main.presentation.interactor.IInteractorCallback;

/**
 * Created by Jerome on 2017/7/25.
 */

public interface IMessageInteractor<T> {
    String doMessage(IInteractorCallback<T> callback,
                     String phone,
                     String type);

}
