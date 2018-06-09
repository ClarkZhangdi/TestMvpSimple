package crm.wangjin.main.domain.repository.network.http;

import android.support.annotation.Nullable;

import crm.wangjin.main.domain.repository.network.IBaseRequest;
import crm.wangjin.main.domain.repository.network.IBaseResp;

/**
 * Created by liuchengen on 2016/12/16.
 */

public interface IOffLienCallback<T extends IBaseRequest, K extends IBaseResp> {

    String onPost(T req, K resp, IHttpCallBack callBack);

    String onGet(@Nullable T req, K resp, IHttpCallBack callBack);
}
