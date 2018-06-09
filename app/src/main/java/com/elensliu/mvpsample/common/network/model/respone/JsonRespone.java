package com.elensliu.mvpsample.common.network.model.respone;


import crm.wangjin.main.domain.repository.network.http.impl.AbsHttpRespone;

/**
 * Created by elensliu on 16/10/24.
 */

public class JsonRespone<T extends BaseRespEntity> extends AbsHttpRespone<T> {

    private static final long serialVersionUID = 849381883256875907L;


    public JsonRespone (T bean) {
        setBean (bean);
    }

}
