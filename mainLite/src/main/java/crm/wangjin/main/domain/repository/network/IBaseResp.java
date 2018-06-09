package crm.wangjin.main.domain.repository.network;

import java.io.Serializable;

import crm.wangjin.main.domain.repository.network.factory.IResponeFactory;


/**
 * Created by elensliu on 16/10/19.
 */
public interface IBaseResp<T, K> extends Serializable {


    void setFactory(IResponeFactory<T, K> factory);
}
