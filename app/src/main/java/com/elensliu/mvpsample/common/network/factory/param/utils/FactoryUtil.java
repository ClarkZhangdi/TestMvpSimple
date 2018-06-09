package com.elensliu.mvpsample.common.network.factory.param.utils;


import android.text.TextUtils;

import com.elensliu.mvpsample.common.network.ResultCode;
import com.elensliu.mvpsample.common.network.ServiceContext;
import com.elensliu.mvpsample.common.network.model.request.BaseReqEntity;
import com.elensliu.mvpsample.common.network.model.respone.BaseRespEntity;
import com.elensliu.mvpsample.common.network.model.respone.JsonRespone;
import com.elensliu.mvpsample.common.network.model.respone.RespBody;
import com.elensliu.mvpsample.common.network.security.AES;
import com.elensliu.mvpsample.common.network.security.RSA;
import com.elensliu.mvpsample.common.utils.JsonUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import crm.wangjin.main.domain.ioc.IOCService;
import crm.wangjin.main.domain.repository.network.IBaseResp;
import crm.wangjin.main.domain.utils.Base64;
import crm.wangjin.main.domain.utils.LogUtil;

import static com.elensliu.mvpsample.common.network.factory.ICommonParamFactory.KEY_AES;
import static com.elensliu.mvpsample.common.network.factory.ICommonParamFactory.KEY_BODY;


/**
 * Created by liuchengen on 2016/12/23.
 */
public class FactoryUtil {

    public static String beanRSAToString(String aesKey,
                                         BaseReqEntity baseBean) throws NoSuchAlgorithmException,
            IOException, InvalidKeySpecException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchPaddingException,
            InvalidKeyException {
        JsonObject body = JsonUtils.gson()
                .toJsonTree(baseBean)
                .getAsJsonObject();
        String bodyStr = body.toString();

        LogUtil.w("bean : key = " + aesKey + " body = " + bodyStr);
        byte[] buf = RSA.encrypt(
                RSA.getRSAPublicKey(),
                aesKey.getBytes());
        String cipherKey = Base64.encode(buf);
        String cipherbody = AES.encrypt(aesKey, bodyStr);

        JsonObject json = new JsonObject();
        json.addProperty(KEY_AES, cipherKey);
        json.addProperty(KEY_BODY, cipherbody);
        return json.toString();
    }

    public static String beanToJson(BaseReqEntity baseBean, String key) {

        JsonObject json = new JsonObject();
        JsonObject body = JsonUtils.gson()
                .toJsonTree(baseBean)
                .getAsJsonObject();
        String sessionId = ServiceContext.getSessionId();
        if (TextUtils.isEmpty(sessionId)) {
            json.addProperty(KEY_AES, key);
        }
        json.add(KEY_BODY, body);
        String jsonStr = json.toString();
        LogUtil.w("request body : " + jsonStr);
        return jsonStr;
    }


    public static <T extends RespBody> void parseBean(IBaseResp<BaseRespEntity<T>, String> baseResp,
                                                      String result) throws Exception {

        if (!(baseResp instanceof JsonRespone)) {

            throw new RuntimeException("错误的 JsonRespone 类型");
        }

        LogUtil.w("回调结果 : " + result);
        JsonRespone jsonRespone = (JsonRespone) baseResp;
        JsonObject object = new JsonParser().parse(result)
                .getAsJsonObject();

        BaseRespEntity baseBean = (BaseRespEntity) jsonRespone.getBean();
        BaseRespEntity tmpBean = JsonUtils.gson()
                .fromJson(object,
                        baseBean.getClass());
        LogUtil.w("解析结果  : " + object.toString());
        if (tmpBean.getRespCode() >= ResultCode.SUCCESS) {

            tmpBean.setBody(IOCService.doInject(tmpBean.getBody()));
            baseBean.setRespMsg(tmpBean.getRespMsg());
            baseBean.setRespCode(tmpBean.getRespCode());
            baseBean.setSessionId(tmpBean.getSessionId());
            baseBean.setBody(tmpBean.getBody());
            jsonRespone.setBean(baseBean);

        }

    }
}
