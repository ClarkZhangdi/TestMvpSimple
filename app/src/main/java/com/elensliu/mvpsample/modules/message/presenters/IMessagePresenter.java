package com.elensliu.mvpsample.modules.message.presenters;

import com.elensliu.mvpsample.modules.message.model.MessageResp;

import crm.wangjin.main.presentation.presenter.IBasePresenter;
import crm.wangjin.main.presentation.presenter.IBaseView;

/**
 * Created by Jerome on 2017/7/25.
 */

public interface IMessagePresenter extends IBasePresenter {

    interface View extends IBaseView {
        void onSendMessageSuccess(MessageResp data);

        void onSendMessageError(String errorMsg);

        void onVerifyTip(String tipMsg);
    }

    void sendMessage(String phone, String type);

    void verifyPhone(String phoneNum);

}
