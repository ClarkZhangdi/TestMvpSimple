package com.elensliu.mvpsample.common.network.factory;


import crm.wangjin.main.domain.repository.network.factory.IParamFactory;
import crm.wangjin.main.domain.repository.network.factory.IResponeFactory;
import crm.wangjin.main.domain.repository.network.http.impl.IHttpParam;

/**
 * Created by elensliu on 16/10/21.
 */

public interface ICommonParamFactory<T> extends IParamFactory<IHttpParam>, IResponeFactory<T, String> {


    String KEY_AES = "key";
    String KEY_BODY = "body";
    String KEY_HEAD = "head";
    String KEY_CONTENT = "content";


}
