package com.test.mvvmretro.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Todos> todosList);

    @Query("SELECT * FROM TBL_TODOS")
    LiveData<List<Todos>> getAllTodos();

    @Query("DELETE FROM TBL_TODOS")
    void deleteAll();
}
