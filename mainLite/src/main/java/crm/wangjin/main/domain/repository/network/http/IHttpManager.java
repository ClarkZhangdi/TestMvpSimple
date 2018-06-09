package crm.wangjin.main.domain.repository.network.http;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import crm.wangjin.main.domain.repository.network.IBaseRequest;
import crm.wangjin.main.domain.repository.network.IBaseResp;


/**
 * Created by elensliu on 16/10/19.
 */
public interface IHttpManager<T extends IBaseRequest, K extends IBaseResp> {

    int Method_GET = 0;
    int Method_POST = 1;

    @IntDef({Method_GET, Method_POST})
    @Retention(RetentionPolicy.SOURCE)
    @interface HttpMethod {
    }


    String get(String url, @Nullable T req, K resp, IHttpCallBack callBack);

    String post(String url, T req, K resp, IHttpCallBack callBack);

    boolean checkNetwork();

    boolean cancelByTag(Object tag);

    void cancelAll();

    void exitAll();

    void setOffLineCallback(IOffLienCallback callback);


}
