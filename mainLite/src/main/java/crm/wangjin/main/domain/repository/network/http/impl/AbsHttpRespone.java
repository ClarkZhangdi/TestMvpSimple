package crm.wangjin.main.domain.repository.network.http.impl;


import crm.wangjin.main.domain.repository.network.IBaseResp;
import crm.wangjin.main.domain.repository.network.factory.IResponeFactory;

/**
 * Created by elensliu on 16/10/19.
 */
public abstract class AbsHttpRespone<T> implements IBaseResp<T, String> {

    private static final long serialVersionUID = -2458686555546356313L;

    private String result;
    private T bean;
    private IResponeFactory<T, String> iResponeFactory;


    @Override
    public void setFactory(IResponeFactory<T, String> factory) {

        iResponeFactory = factory;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) throws Exception {

        this.result = result;
        iResponeFactory.parseBean(this,
                result);

    }

}
