package com.elensliu.mvpsample.common.network;


/**
 * Created by elensliu on 16/10/21.
 */

public class ServiceContext {


    static String sessionId;

    public static String getSessionId() {


        return sessionId;
    }

    public static void setSessionId(String sessionId) {

        ServiceContext.sessionId = sessionId;
    }

}
