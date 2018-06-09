package com.elensliu.mvpsample.common.network.model.request;


import crm.wangjin.main.domain.repository.network.http.impl.AbsHttpRequest;

/**
 * Created by elensliu on 16/10/19.
 */
public class JsonRequest<T extends BaseReqEntity> extends AbsHttpRequest {

    private static final long serialVersionUID = 8648834167685557023L;

    private T requestBean;

    public JsonRequest (T bean) {

        requestBean = bean;
    }

    public T getBean () {

        return requestBean;
    }


}
