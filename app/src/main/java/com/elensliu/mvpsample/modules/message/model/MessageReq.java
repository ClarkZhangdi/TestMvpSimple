package com.elensliu.mvpsample.modules.message.model;

import com.elensliu.mvpsample.common.network.model.request.BaseReqEntity;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerome on 2017/7/25.
 */

public class MessageReq extends BaseReqEntity<MessageReq.Content> {

    @Override
    protected String defaultTag() {
        return null;
    }

    public static class Content {


        @SerializedName("phone")
        String mobile;

        @SerializedName("type")
        String type;

        public Content(String mobile) {

            this.mobile = mobile;
            type = "1";
        }


        public Content(String mobile, String type) {
            this.mobile = mobile;
            this.type = type;
        }
    }

    public MessageReq(String phone,String type) {

        MessageReq.Content content = new MessageReq.Content(phone,
                type);
        setContent(content);
    }
}
