package com.example.nikhilkgupta.pt.APICalls;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Nikhilk.Gupta on 24-09-2018.
 */

public interface APIInterface {

    @POST
    Call<Response> getUserVerify(@HeaderMap Map<String , String> headers , @Url String url, @Body Request request);

//    @POST
//     Call<Response> getUserVerify(@HeaderMap Map<String , String> headers , @Url String url, @Body Request request);

}
