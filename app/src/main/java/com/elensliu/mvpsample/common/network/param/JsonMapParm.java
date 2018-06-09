package com.elensliu.mvpsample.common.network.param;


import com.elensliu.mvpsample.common.network.ServiceContext;

import java.util.HashMap;
import java.util.Map;

import crm.wangjin.main.domain.repository.network.factory.IParamFactory;
import crm.wangjin.main.domain.repository.network.http.impl.IHttpParam;

import static crm.wangjin.main.domain.repository.network.factory.IParamFactory.KEY_SESSION_ID;


/**
 * Created by elensliu on 16/10/21.
 */

public class JsonMapParm implements IHttpParam {


    Map<String, String> entity = new HashMap<>();

    public JsonMapParm(String object) {

        entity.put(IParamFactory.KEY_JSON, object);
        String sessionId = ServiceContext.getSessionId();
        entity.put(KEY_SESSION_ID, sessionId);
    }


    @Override
    public Map<String, String> getEntity() {

        return entity;
    }
}
