package com.elensliu.mvpsample.common.network.callback;


import android.text.TextUtils;

import com.elensliu.mvpsample.common.network.ResultCode;
import com.elensliu.mvpsample.common.network.ServiceContext;
import com.elensliu.mvpsample.common.network.model.respone.BaseRespEntity;
import com.elensliu.mvpsample.common.network.model.respone.RespBody;

import crm.wangjin.main.domain.repository.network.http.IHttpCallBack;
import crm.wangjin.main.domain.repository.network.http.impl.AbsHttpRespone;
import crm.wangjin.main.presentation.interactor.IInteractorCallback;

import static com.elensliu.mvpsample.common.network.ResultCode.AUTO_SYNC_AUTH_FAIL;


/**
 * Created by elensliu on 16/10/24.
 */

public class NetworkCallback<T> implements IHttpCallBack<AbsHttpRespone<T>> {


    private final IInteractorCallback<T> callBack;

    public NetworkCallback(IInteractorCallback<T> callBack) {

        this.callBack = callBack;
    }

    @Override
    public void onBefore() {

        callBack.beforeRequest();
    }

    @Override
    public void onSuccess(AbsHttpRespone<T> response) {

        BaseRespEntity entity = (BaseRespEntity) response.getBean();
        int respCode = entity.getRespCode();
        String respMsg = entity.getRespMsg();
        String sessinId = entity.getSessionId();

        if (!TextUtils.isEmpty(sessinId)) {

            ServiceContext.setSessionId(sessinId);
        }

        if (respCode >= ResultCode.SUCCESS) {
            RespBody body = entity.getBody();
            int statusCode = body.getStatusCode();
            if (statusCode >= ResultCode.SUCCESS || statusCode == AUTO_SYNC_AUTH_FAIL) {
                callBack.success(response.getBean());
            } else {
                onFailure(body.getStatusMsg(),
                        statusCode);
            }
        } else {

            onFailure(respMsg,
                    respCode);

        }
    }

    @Override
    public void onFailure(String errorStr, int code) {

        callBack.onError(errorStr, code);
    }
}
