package crm.wangjin.main.domain.repository.network.http.impl.retrofit.service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by liuchengen on 2017/4/25.
 */

public interface PostService {

    @POST
    Call<String> postBody(
            @Url String url,
            @Body RequestBody json);
}
