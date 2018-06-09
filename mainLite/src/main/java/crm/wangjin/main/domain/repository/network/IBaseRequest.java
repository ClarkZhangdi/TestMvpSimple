package crm.wangjin.main.domain.repository.network;

import android.support.annotation.IntDef;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import crm.wangjin.main.domain.repository.network.factory.IParamFactory;


/**
 * Created by elensliu on 16/10/19.
 */
public interface IBaseRequest<T extends IParam> extends Serializable {


    int Strategy_Singleton = 0; //每次请求队列中只保留一个对象
    int Strategy_Delayed = 1; //每次请求队列中若存在相同对象则新的请求会延时请求，默认500毫秒


    /**
     * 网络请求策略
     */
    @IntDef({Strategy_Singleton, Strategy_Delayed})
    @Retention(RetentionPolicy.SOURCE)
    @interface RequestStrategy {
    }

    void setRequestStrategy(@RequestStrategy int strategy);

    @RequestStrategy
    int getRequestStrategy();

    void setParamFactory(IParamFactory<T> factory);

    T getParams() throws Exception;

    String getTag();

    long getRequestTime();

    void setRequestTime(long requestTime);


}
