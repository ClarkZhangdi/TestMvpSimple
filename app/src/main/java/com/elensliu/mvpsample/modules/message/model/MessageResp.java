package com.elensliu.mvpsample.modules.message.model;

import com.elensliu.mvpsample.common.network.model.respone.BaseRespEntity;
import com.google.gson.annotations.SerializedName;

import crm.wangjin.main.domain.ioc.inject.NotNull;

/**
 * Created by Jerome on 2017/7/25.
 */

public class MessageResp extends BaseRespEntity<MessageResp.Content> {

    @NotNull
    public static class Content {


        @SerializedName("statusCode")
        String statusCode;

        @SerializedName("statusMessage")
        String statusMessage;

        @Override
        public String toString() {
            return "Content{" +
                    "statusCode='" + statusCode + '\'' +
                    ", statusMessage='" + statusMessage + '\'' +
                    '}';
        }
    }

    public MessageResp() {
        Content content = new Content();
        setContent(content);
    }
}
