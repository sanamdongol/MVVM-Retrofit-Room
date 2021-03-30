package com.test.mvvmretro.data.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.test.mvvmretro.App;
import com.test.mvvmretro.data.api.ApiService;
import com.test.mvvmretro.data.api.RetrofitConfig;
import com.test.mvvmretro.data.db.AppDatabase;
import com.test.mvvmretro.data.db.TodoDao;
import com.test.mvvmretro.data.db.Todos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoRepository implements TodoRepoImpl {
    private TodoDao todoDao;
    private MutableLiveData<List<Todos>> todosMutableLiveData;

    public TodoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        todoDao = db.todoDao();
        todosMutableLiveData = new MutableLiveData<>();
    }

    //call this method for non postman
    /*public void getTodoList() {

        ApiService apiService = RetrofitConfig.getRetrofitClient().create(ApiService.class);
        Call<List<Todos>> call = apiService.getAllTodoList();
        call.enqueue(new Callback<List<Todos>>() {
            @Override
            public void onResponse(Call<List<Todos>> call, Response<List<Todos>> response) {
                if (response.isSuccessful()) {
                    todosMutableLiveData.postValue(response.body());
                     saveTodos(response.body());
                } else {
                    todosMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Todos>> call, Throwable t) {
                todosMutableLiveData.postValue(null);
            }
        });
    }*/

    public void getTodoList() {

        ApiService apiService = RetrofitConfig.getRetrofitClient().create(ApiService.class);
        Call<ResponseBody> call = apiService.getAllTodoList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        Log.e("api res", json.toString());
                        String newDate = json.getString("modify_date");
                      //  json.getString("page");
                      //  Log.e("modify", newDate);

                        String lastDate = getLastDate();
                        //store date only if it is null
                        if (lastDate.equals("empty")) {
                            storeModifyDate(newDate);
                            List<Todos> todosList = new ArrayList<>();
                            JSONArray array = json.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Gson gson = new Gson();
                                Todos todosData = gson.fromJson(String.valueOf(obj), Todos.class);
                                todosList.add(todosData);
                            }
                            saveTodos(todosList);
                        }

                        if (!lastDate.equalsIgnoreCase(newDate)) {
                            List<Todos> todosList = new ArrayList<>();
                            JSONArray array = json.getJSONArray("data");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                Gson gson = new Gson();
                                Todos todosData = gson.fromJson(String.valueOf(obj), Todos.class);
                                todosList.add(todosData);
                            }
                            saveTodos(todosList);
                        }


                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void storeModifyDate(String modifyDate) {
        SharedPreferences preferences = App.getContext().getSharedPreferences("Pref_Date", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("modify_date", modifyDate);
        editor.commit();
    }

    private String getLastDate() {
        SharedPreferences preferences = App.getContext().getSharedPreferences("Pref_Date", Context.MODE_PRIVATE);
        return preferences.getString("modify_date", "empty");
    }

    private void saveTodos(List<Todos> todoList) {
        new SaveTodo(todoDao).execute(todoList);
    }

    @Override
    public void getAll() {

    }

    @Override
    public void detel() {

    }


    private static class SaveTodo extends AsyncTask<List<Todos>, Void, Void> {
        private TodoDao todoDao;

        public SaveTodo(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(List<Todos>... lists) {
            todoDao.insert(lists[0]);
            return null;
        }
    }

    public LiveData<List<Todos>> getTodosLiveData() {
        return todoDao.getAllTodos();
    }

}
