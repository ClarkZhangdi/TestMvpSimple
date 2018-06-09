package crm.wangjin.main.domain.repository.network.http.impl;


import android.text.TextUtils;

import crm.wangjin.main.domain.repository.network.IBaseRequest;
import crm.wangjin.main.domain.repository.network.factory.IParamFactory;

/**
 * Created by elensliu on 16/10/19.
 */
public abstract class AbsHttpRequest implements IBaseRequest<IHttpParam> {

    private static final long serialVersionUID = 8704763216021980989L;

    private IParamFactory<IHttpParam> iParamFactory;

    private String tag;
    private long requestTime = -1;

    @RequestStrategy
    private int requestStrategy;


    @Override
    public void setRequestStrategy(@RequestStrategy int strategy) {

        this.requestStrategy = strategy;
    }

    @Override
    public int getRequestStrategy() {
        return requestStrategy;
    }

    @Override
    public void setParamFactory(IParamFactory<IHttpParam> factory) {

        iParamFactory = factory;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getTag() {

        if (TextUtils.isEmpty(tag)) {

            tag = this.getClass().toString() + System.currentTimeMillis();
        }

        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public IHttpParam getParams() throws Exception {

        IHttpParam param;
        if (iParamFactory == null) {
            throw new RuntimeException("IParamFactory is not nullable");
        }
        param = iParamFactory.buildParms(this);

        return param;
    }
}
