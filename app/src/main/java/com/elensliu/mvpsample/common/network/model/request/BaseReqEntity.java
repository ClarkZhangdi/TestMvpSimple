package com.elensliu.mvpsample.common.network.model.request;

import com.elensliu.mvpsample.common.network.HeadManager;
import com.elensliu.mvpsample.common.network.factory.ICommonParamFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by elensliu on 16/10/20.
 */

public abstract class BaseReqEntity<T> {

    @Expose
    private String defaultTag = "default";

    @SerializedName(ICommonParamFactory.KEY_CONTENT)
    T content;

    @SerializedName(ICommonParamFactory.KEY_HEAD)
    ReqHead reqHead;


    public BaseReqEntity() {

        reqHead = new ReqHead();
        HeadManager.defaultParams(reqHead);
        defaultTag = defaultTag();
    }

    protected abstract String defaultTag();


    public void setContent(T body) {
        this.content = body;
    }

    public T getContent() {
        return content;
    }


    public String getDefaultTag() {
        return defaultTag;
    }

    public ReqHead getReqHead() {
        return reqHead;
    }
}
