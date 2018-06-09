package crm.wangjin.main.domain.repository.network.factory;


import crm.wangjin.main.domain.repository.network.IBaseRequest;
import crm.wangjin.main.domain.repository.network.IParam;

/**
 * Created by elensliu on 16/10/19.
 */
public interface IParamFactory<T extends IParam> {

    String KEY_JSON = "json";
    String KEY_SESSION_ID = "session_id";

    T buildParms(IBaseRequest request) throws Exception;


}
