package com.test.mvvmretro.data.api;

import com.test.mvvmretro.data.db.Todos;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    /*@GET(EndPoints.TODO_LIST)
    Call<List<Todos>> getAllTodoList();*/

    @GET(EndPoints.TODO_LIST)
    Call<ResponseBody> getAllTodoList();

}
