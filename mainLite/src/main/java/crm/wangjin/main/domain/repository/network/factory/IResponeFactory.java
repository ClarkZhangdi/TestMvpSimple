package crm.wangjin.main.domain.repository.network.factory;


import crm.wangjin.main.domain.repository.network.IBaseResp;

/**
 * Created by elensliu on 16/10/25.
 */

public interface IResponeFactory<T, K> {

    void parseBean(IBaseResp<T, K> baseResp, K result) throws Exception;
}
