package com.test.mvvmretro.pagination;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(EndPoint.LIST)
    Call<ResponseBody> getList(
            @Query("page") int page,
            @Query("limit") int limit

    );
}
