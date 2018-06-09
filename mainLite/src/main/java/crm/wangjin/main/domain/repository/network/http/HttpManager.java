package crm.wangjin.main.domain.repository.network.http;


import crm.wangjin.main.domain.repository.network.http.impl.retrofit.RetrofitManager;

/**
 * Created by elensliu on 16/10/19.
 */
public class HttpManager {

    public static final int MODE_NO_HTTP = 1;

    public static final int MODE_OK_HTTP = 2;
    public static final int MODE_RETROFIT = 3;


    private static final int DEFAULT_CONNECTTIMEOUT = 30 * 1000;
    private static final int DEFAULT_READTIMEOUT = 30 * 1000;

    private static IHttpManager client = null;

    private HttpManager() {
    }

    public static class IHttpManagerBuilder {


        private int mConnectTimeout = DEFAULT_CONNECTTIMEOUT;
        private int mReadTimeout = DEFAULT_READTIMEOUT;

        private IHttpManagerBuilder() {
        }

        public void setConnectTimeout(int mConnectTimeout) {
            this.mConnectTimeout = mConnectTimeout;
        }

        public void setReadTimeout(int mReadTimeout) {
            this.mReadTimeout = mReadTimeout;
        }

        public IHttpManager create(int mode) {


            switch (mode) {
                
                case MODE_RETROFIT:
                    client = RetrofitManager.getInstance(mConnectTimeout,
                            mReadTimeout);
                    break;
            }

            return client;
        }

    }

    public static IHttpManagerBuilder builder() {

        return new IHttpManagerBuilder();
    }


    public static void setOffLineMode(IOffLienCallback callback) {
        if (client == null) {

            throw new RuntimeException("必须先调用builder创建对象");
        }
        client.setOffLineCallback(callback);
    }

    public static IHttpManager getClient() {

        if (client == null) {

            throw new RuntimeException("必须先调用builder创建对象");
        }
        return client;
    }

    public static void exit() {

        if (client == null) {

            throw new RuntimeException("必须先调用builder创建对象");
        }
        client.exitAll();
    }

}
