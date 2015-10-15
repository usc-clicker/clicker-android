package edu.usc.clicker.api;

import com.squareup.okhttp.ResponseBody;

import edu.usc.clicker.model.LoginBody;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

public interface LoginAPI {
    @POST("/auth/login/")
    Call<ResponseBody> login(@Body LoginBody loginBody);
}
