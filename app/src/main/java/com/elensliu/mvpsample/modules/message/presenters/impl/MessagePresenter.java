package com.elensliu.mvpsample.modules.message.presenters.impl;

import com.elensliu.mvpsample.common.utils.RegexUtils;
import com.elensliu.mvpsample.modules.message.interactors.IMessageInteractor;
import com.elensliu.mvpsample.modules.message.interactors.impl.MessageInteractor;
import com.elensliu.mvpsample.modules.message.model.MessageResp;
import com.elensliu.mvpsample.modules.message.presenters.IMessagePresenter;

import javax.inject.Inject;

import crm.wangjin.main.domain.utils.LogUtil;
import crm.wangjin.main.presentation.presenter.AbsBasePresenter;

/**
 * Created by Jerome on 2017/7/25.
 */

public class MessagePresenter extends AbsBasePresenter<IMessagePresenter.View, MessageResp>
        implements IMessagePresenter {

    private IMessageInteractor<MessageResp> messageInteractor;

    @Inject
    public MessagePresenter(MessageInteractor messageInteractor) {
        this.messageInteractor = messageInteractor;
    }

    @Override
    public void verifyPhone(String phoneNum) {

        boolean isPhone = RegexUtils.isMobileExact(phoneNum);
        if (!isPhone) {
            getBaseView().onVerifyTip("不是有效的手机号码");
        } else {
            getBaseView().onVerifyTip("");
        }
    }


    @Override
    public void beforeRequest() {
        getBaseView().showProgress();
    }

    @Override
    public void sendMessage(String phoneNum, String type) {
        boolean isPhone = RegexUtils.isMobileExact(phoneNum);
        if (!isPhone) {
            getBaseView().showMsg("不是有效的手机号码");
            return;
        }
        String tag = this.messageInteractor.doMessage(this, phoneNum, type);
        LogUtil.w(tag);
        addTaskTag(tag);
    }

    @Override
    public void success(MessageResp data) {
        super.success(data);
        getBaseView().hideProgress();
        getBaseView().onSendMessageSuccess(data);
    }


    @Override
    public void onError(String errorMsg,
                        int errorCode) {

        getBaseView().hideProgress();
        if (errorCode == 1) {
            getBaseView().onSendMessageError("处理失败");
        }
        getBaseView().onSendMessageError(errorMsg);
    }
}
