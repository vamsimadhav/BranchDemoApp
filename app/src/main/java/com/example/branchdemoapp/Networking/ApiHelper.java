package com.example.branchdemoapp.Networking;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.example.branchdemoapp.Networking.Interface.ApiCompletionCallback;
import com.example.branchdemoapp.Networking.Interface.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHelper {

    private static final String branchKey = "key_live_exjhLozOYkDgehiHDSq0SgdeCFnd9sfi";
    private static final String androidVersion =  Build.VERSION.RELEASE;

    public static void sendStandardEvent(Context context, ApiCompletionCallback callback){
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // Create a JSON object with the Android ID variable
        MediaType mediaType = MediaType.parse("application/json");

        JSONObject jsonData = new JSONObject();

        try {
            jsonData.put("name", "VIEW_ITEM");

            JSONObject userData = new JSONObject();
            userData.put("os", "Android");
            userData.put("os_version", androidVersion);
            userData.put("android_id", androidId);

            jsonData.put("user_data", userData);
            jsonData.put("branch_key", branchKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(mediaType, jsonData.toString());
       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

       Call<ResponseBody> call = apiInterface.sendApiStandardEvent(body);

       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if (response.isSuccessful()){
                   callback.onCompletion(true);
               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });

    }
}
