package com.example.nikhilkgupta.pt.APICalls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikhilk.Gupta on 24-09-2018.
 */

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String BASE_URL){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

