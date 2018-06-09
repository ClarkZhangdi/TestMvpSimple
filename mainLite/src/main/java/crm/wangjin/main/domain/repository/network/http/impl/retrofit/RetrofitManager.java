package crm.wangjin.main.domain.repository.network.http.impl.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import crm.wangjin.main.domain.dagger.inject.AppInject;
import crm.wangjin.main.domain.executor.IExecutorCallback;
import crm.wangjin.main.domain.repository.network.http.IHttpCallBack;
import crm.wangjin.main.domain.repository.network.http.IHttpManager;
import crm.wangjin.main.domain.repository.network.http.IOffLienCallback;
import crm.wangjin.main.domain.repository.network.http.RequestManager;
import crm.wangjin.main.domain.repository.network.http.impl.AbsHttpRequest;
import crm.wangjin.main.domain.repository.network.http.impl.AbsHttpRespone;
import crm.wangjin.main.domain.repository.network.http.impl.retrofit.service.PostService;
import crm.wangjin.main.domain.utils.LogUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static crm.wangjin.main.domain.repository.network.factory.IParamFactory.KEY_JSON;
import static crm.wangjin.main.domain.repository.network.factory.IParamFactory.KEY_SESSION_ID;

/**
 * Created by liuchengen on 2017/4/25.
 */

public class RetrofitManager implements
        IHttpManager<AbsHttpRequest, AbsHttpRespone> {


    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient mOkHttpClient;
    private static RetrofitManager manager;
    private static MyInterceptor interceptor = new MyInterceptor();


    private static class MyInterceptor implements Interceptor {

        private String session = "";

        public void setSession(String session) {

            this.session = session;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Cookie", "session_id=" + session)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    }


    private IOffLienCallback<AbsHttpRequest, AbsHttpRespone> offLienCallback;

    private void init(int connecttime,
                      int readtimeout) {

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connecttime,
                        TimeUnit.MILLISECONDS)
                .readTimeout(readtimeout,
                        TimeUnit.MILLISECONDS)
                .writeTimeout(readtimeout,
                        TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
        RequestManager.start();


    }

    public synchronized static RetrofitManager getInstance(int connecttime,
                                                           int readtimeout) {

        if (manager == null) {

            manager = new RetrofitManager();
            manager.init(connecttime,
                    readtimeout);
        }

        return manager;
    }


    @Override
    public String get(String url, @Nullable AbsHttpRequest req,
                      AbsHttpRespone resp,
                      IHttpCallBack callBack) {
        return null;
    }

    @Override
    public String post(final String url,
                       final AbsHttpRequest req,
                       final AbsHttpRespone resp,
                       final IHttpCallBack callBack) {

        if (!checkNetwork() && offLienCallback == null) {
            callBack.onFailure("网络不可用", -1);
            return "";
        }
        callBack.onBefore();
        if (offLienCallback != null) {
            String tag = offLienCallback.onPost(req, resp, callBack);
            return tag;
        }
        IExecutorCallback callback = new IExecutorCallback() {

            boolean isSuccess = true;
            String errorMsg = "";
            String requestTag = "";

            @Override
            public void runOnBackGround() {

                requestTag = req.getTag();
                try {
                    Map<String, String> params = req.getParams()
                            .getEntity();
                    String json = params.get(KEY_JSON);
                    String sessionId = params.get(KEY_SESSION_ID);
                    if (!TextUtils.isEmpty(sessionId)) {
                        interceptor.setSession(sessionId);
                    }
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(mOkHttpClient)
                            .build();
                    PostService postService = retrofit.create(PostService.class);
                    RequestBody requestBody = RequestBody.create(JSON, json);
                    Call<String> call = postService.postBody(url, requestBody);
                    Response<String> response = call.execute();
                    resp.setResult(response.body());

                } catch (IOException e) {
                    LogUtil.w("网络错误 :" + e.getLocalizedMessage());
                    isSuccess = false;
                    errorMsg = "网络出错，请重试";

                } catch (Exception e) {
                    LogUtil.w("网络错误 :" + e.getLocalizedMessage());
                    isSuccess = false;
                    errorMsg = e.getLocalizedMessage();
                }
            }

            @Override
            public void runOnMainFinsh() {

                if (isSuccess) {
                    callBack.onSuccess(resp);
                } else {
                    callBack.onFailure(errorMsg, -999);
                }

                RequestManager.removeUnFinish(requestTag);
            }

            @Override
            public void cancel() {

                RequestManager.removeUnFinish(requestTag);
            }
        };

        String tag = RequestManager.addRequest(req,
                callback);
        return tag;
    }

    @Override
    public boolean checkNetwork() {

        Context context = AppInject.getInstance().getApplication().getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean cancelByTag(Object tag) {
        return RequestManager.cancelByTag(tag);
    }

    @Override
    public void cancelAll() {
        RequestManager.cancelAll();
    }

    @Override
    public void exitAll() {
        RequestManager.stop();
    }

    @Override
    public void setOffLineCallback(IOffLienCallback callback) {
        offLienCallback = callback;
    }
}
