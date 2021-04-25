package com.test.mvvmretro.recyclerview.normarecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.test.mvvmretro.R;

import java.util.ArrayList;
import java.util.List;

public class RegularRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        List<MyRegularData> data = fetchData();


        RegularAdapter adapter = new RegularAdapter(data);
       /* LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
       */

       /* GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);*/

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private List<MyRegularData> fetchData() {
        List<MyRegularData> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MyRegularData model = new MyRegularData();
            if (i % 3 == 0) {
                model.setTitle("Title jdfa asjhf ajdfka  hsf" + i);
            } else {
                model.setTitle("Title " + i);
            }

            dataList.add(model);
        }
        return dataList;
    }
}

class MyRegularData {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}