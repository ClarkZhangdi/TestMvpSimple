package com.elensliu.mvpsample.common.network.model.respone;

import com.google.gson.annotations.SerializedName;

import crm.wangjin.main.domain.ioc.inject.NotNull;

/**
 * Created by liuchengen on 2016/12/23.
 */

@NotNull
public class RespBody<T> {

    @SerializedName("statusCode")
    protected int statusCode;

    @SerializedName("statusMsg")
    protected String statusMsg;

    @SerializedName("content")
    protected T content;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RespBody{" +
                "statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                ", content=" + content +
                '}';
    }
}
