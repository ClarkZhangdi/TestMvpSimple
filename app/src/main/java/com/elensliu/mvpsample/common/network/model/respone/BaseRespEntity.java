package com.elensliu.mvpsample.common.network.model.respone;


import com.google.gson.annotations.SerializedName;


/**
 * Created by elensliu on 16/10/25.
 */

public class BaseRespEntity<T> {

    @SerializedName("respCode")
    int respCode = -1;

    @SerializedName("respMsg")
    String respMsg;

    @SerializedName("session_id")
    String sessionId;

    @SerializedName("body")
    protected RespBody<T> body;

    private Class<?> contentType;


    public BaseRespEntity() {

        body = new RespBody<>();
    }

    public void setContent(T content) {

        body.setContent(content);
        contentType = body.getContent().getClass();
    }

    public void setBody(RespBody<T> body) {
        this.body = body;
    }

    public Class<?> getContentType() {
        return contentType;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public RespBody<T> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "BaseRespEntity{" +
                "respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", body=" + body +
                ", contentType=" + contentType +
                '}';
    }
}
