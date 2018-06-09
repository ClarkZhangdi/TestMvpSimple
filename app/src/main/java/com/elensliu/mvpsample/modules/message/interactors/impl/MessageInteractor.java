package com.elensliu.mvpsample.modules.message.interactors.impl;

import android.content.Context;

import com.elensliu.mvpsample.common.network.HttpUrls;
import com.elensliu.mvpsample.common.network.callback.NetworkCallback;
import com.elensliu.mvpsample.common.network.factory.param.RSAParamFactory;
import com.elensliu.mvpsample.common.network.model.request.JsonRequest;
import com.elensliu.mvpsample.common.network.model.respone.JsonRespone;
import com.elensliu.mvpsample.modules.message.interactors.IMessageInteractor;
import com.elensliu.mvpsample.modules.message.model.MessageReq;
import com.elensliu.mvpsample.modules.message.model.MessageResp;

import javax.inject.Inject;

import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.domain.repository.network.http.HttpManager;
import crm.wangjin.main.domain.repository.network.http.IHttpCallBack;
import crm.wangjin.main.domain.repository.network.http.IHttpManager;
import crm.wangjin.main.presentation.interactor.IInteractorCallback;

/**
 * Created by Jerome on 2017/7/25.
 */

public class MessageInteractor implements IMessageInteractor<MessageResp> {

    IHttpManager manager;

    @Inject
    @ScopeLife("Activity")
    Context mContext;

    @Inject
    public MessageInteractor() {
        manager = HttpManager.getClient();
    }


    private String _doMessage(JsonRequest<MessageReq> req,
                              IInteractorCallback<MessageResp> callBack) {

        RSAParamFactory factory = new RSAParamFactory();
        req.setParamFactory(factory);

        //构造回调格式
        JsonRespone<MessageResp> resp = new JsonRespone<>(new MessageResp());
        resp.setFactory(factory);

        IHttpCallBack<JsonRespone> httpCallBack = new NetworkCallback(callBack);
        return manager.post(HttpUrls.VERIFY_MESSAGE_URL,
                req,
                resp,
                httpCallBack);
    }

    @Override
    public String doMessage(IInteractorCallback<MessageResp> callback, String phone, String type) {
        //构造请求格式
        JsonRequest<MessageReq> req = new JsonRequest<>(new MessageReq(phone,
                type));
        req.setTag(req.getClass().toString() + phone + type);

        return _doMessage(req, callback);
    }
}
