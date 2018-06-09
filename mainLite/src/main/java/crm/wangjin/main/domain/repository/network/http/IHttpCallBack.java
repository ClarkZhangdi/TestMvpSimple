package crm.wangjin.main.domain.repository.network.http;


import crm.wangjin.main.domain.repository.network.IBaseResp;

/**
 * Created by elensliu on 16/10/19.
 */
public interface IHttpCallBack<T extends IBaseResp> {

    void onBefore();

    void onSuccess(T response);

    void onFailure(String errorStr, int code);
}
