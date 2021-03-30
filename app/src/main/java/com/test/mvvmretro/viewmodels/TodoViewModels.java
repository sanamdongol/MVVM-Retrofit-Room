package com.test.mvvmretro.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.mvvmretro.data.db.Todos;
import com.test.mvvmretro.data.repository.TodoRepository;
import com.test.mvvmretro.utils.Utils;

import java.util.List;

public class TodoViewModels extends AndroidViewModel {
    private TodoRepository todoRepository;


    public TodoViewModels(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(application);
    }

    public void getTodoList() {
        if (Utils.isConnected()) {
            Log.e("no internet", "no internet");
            todoRepository.getTodoList();
        }
    }

    public LiveData<List<Todos>> getTodosLiveData() {
        return todoRepository.getTodosLiveData();
    }
}
