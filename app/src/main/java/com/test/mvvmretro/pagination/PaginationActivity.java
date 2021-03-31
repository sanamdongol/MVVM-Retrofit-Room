package com.test.mvvmretro.pagination;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.test.mvvmretro.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaginationActivity extends AppCompatActivity {


    private int page = 1;
    private int limit = 10;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        swipe = findViewById(R.id.swipe);

        adapter = new MyAdapter();
        getDataFromApi(page, limit);


        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
                page++;
                getDataFromApi(page, limit);

            }
        });
    }

    private void getDataFromApi(int page, int limit) {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getList(page, limit);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    try {

                        List<User> userList = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            String id = json.getString("id");
                            String author = json.getString("author");
                            String width = json.getString("width");
                            String url = json.getString("url");

                            User user = new User(id, author, width, url);
                            userList.add(user);

                        }
                        Collections.reverse(userList);
                        adapter.addData(userList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.e("Error", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                call.cancel();
            }
        });

    }
}