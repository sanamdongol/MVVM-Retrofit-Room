package com.test.mvvmretro.data.api;

import okhttp3.internal.http1.Http1ExchangeCodec;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    //   public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public static final String BASE_URL = "https://6d3df76f-2cc1-4590-b25f-b017438b6339.mock.pstmn.io/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
