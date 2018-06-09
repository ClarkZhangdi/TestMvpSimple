package com.elensliu.mvpsample.common.network.factory.param;


import com.elensliu.mvpsample.common.network.factory.ICommonParamFactory;
import com.elensliu.mvpsample.common.network.factory.param.utils.FactoryUtil;
import com.elensliu.mvpsample.common.network.model.request.BaseReqEntity;
import com.elensliu.mvpsample.common.network.model.request.JsonRequest;
import com.elensliu.mvpsample.common.network.model.respone.BaseRespEntity;
import com.elensliu.mvpsample.common.network.model.respone.RespBody;
import com.elensliu.mvpsample.common.network.param.JsonMapParm;
import com.elensliu.mvpsample.common.network.security.AES;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import crm.wangjin.main.domain.repository.network.IBaseRequest;
import crm.wangjin.main.domain.repository.network.IBaseResp;
import crm.wangjin.main.domain.repository.network.http.impl.IHttpParam;

/**
 * Created by elensliu on 16/10/21.
 */

//RSA封装工厂
public class RSAParamFactory<T extends RespBody> implements ICommonParamFactory<BaseRespEntity<T>> {

    private String aesKey;


    @Override
    public IHttpParam buildParms(IBaseRequest request) throws Exception {
        if (!(request instanceof JsonRequest)) {

            throw new RuntimeException("错误的 JsonRequst 类型");
        }

        JsonRequest jsonRequst = (JsonRequest) request;
        BaseReqEntity baseBean = jsonRequst.getBean();
        JsonMapParm param = new JsonMapParm(beanToString(baseBean));
        return param;
    }

    private String beanToString(BaseReqEntity baseBean)
            throws NoSuchAlgorithmException,
            IOException, InvalidKeySpecException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchPaddingException,
            InvalidKeyException {
        aesKey = AES.generateKey();

        return FactoryUtil.beanToJson(baseBean, aesKey);

    }

    @Override
    public void parseBean(IBaseResp<BaseRespEntity<T>,
            String> baseResp,
                          String result)
            throws Exception {

        FactoryUtil.parseBean(baseResp, result);

    }

}
