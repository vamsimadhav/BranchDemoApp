package com.example.branchdemoapp.Networking;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({
            "accept: application/json",
            "content-type: application/json"
    })
    @POST("v2/event/standard")
    Call<ResponseBody> sendApiStandardEvent(@Body RequestBody requestBody);
}
