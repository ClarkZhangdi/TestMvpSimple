package com.elensliu.mvpsample.common.network;

/**
 * Created by liuchengen on 2016/12/12.
 */

public interface ResultCode {

    /**
     * 访问成功
     */
    int SUCCESS = 0;


    /**
     * 第三方账号未绑定手机
     */
    int ACCOUNT_NO_BIND = -10022;

    /**
     * 用户未登录
     */
    int ACCOUNT_NO_LOGIN = -10004;

    /**
     * 自动同步授权失败
     */
    int AUTO_SYNC_AUTH_FAIL = -10029;
}
