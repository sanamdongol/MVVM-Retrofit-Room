package com.test.mvvmretro.ui.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;
import com.test.mvvmretro.data.db.Todos;
import com.test.mvvmretro.data.repository.TodoRepository;
import com.test.mvvmretro.ui.adapter.TodosAdapter;
import com.test.mvvmretro.viewmodels.TodoViewModels;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodoViewModels viewModel;
    private RecyclerView recyclerView;
    private TodoRepository repository;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
     //   progressBar = findViewById(R.id.progressBar);

        viewModel = new ViewModelProvider(MainActivity.this).get(TodoViewModels.class);
        viewModel.getTodoList();

        TodosAdapter adapter = new TodosAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getTodosLiveData().observe(this, new Observer<List<Todos>>() {
            @Override
            public void onChanged(List<Todos> todoList) {
             //   progressBar.setVisibility(View.GONE);
                if (todoList != null) {
                    for (int i = 0; i < todoList.size(); i++) {
                        adapter.addTodoList(todoList);
                        Log.e("position", i + "============");

                    }
                } else {
                    Log.e("else eror", "============");
                }

            }
        });
    }


}