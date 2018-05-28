package com.udakita.komunitas.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Blackswan on 9/12/2017.
 */

public class MyRetrofitClient {
    private static Retrofit getRetrofit(){
        //insialisasi retrofit 2
        Retrofit r = new Retrofit.Builder()
                .baseUrl("http://192.168.100.12/komunitas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return r;
    }
    public static RestApi getInstaceRetrofit(){
        return getRetrofit().create(RestApi.class);
    }

}
