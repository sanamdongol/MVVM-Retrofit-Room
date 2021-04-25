package com.test.mvvmretro.recyclerview.heterogeneous;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<Object> objects = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.rv_main);

        MainAdapter adapter = new MainAdapter(this, getObject());
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Object> getObject() {
        objects.add(getVerticalData().get(0));
        objects.add(getHorizontalData().get(0));
        return objects;

    }

    public static ArrayList<VerticalModel> getVerticalData() {
        ArrayList<VerticalModel> verticalModels = new ArrayList<>();
        verticalModels.add(new VerticalModel("Title 1", "Desc 1", R.drawable.brave));
        verticalModels.add(new VerticalModel("Title 2", "Desc 2", R.drawable.brave));
        verticalModels.add(new VerticalModel("Title 3", "Desc 3", R.drawable.brave));
        verticalModels.add(new VerticalModel("Title 4", "Desc 4", R.drawable.brave));
        verticalModels.add(new VerticalModel("Title 5", "Desc 5", R.drawable.brave));
        return verticalModels;

    }

    public static ArrayList<HorizontalModel> getHorizontalData() {
        ArrayList<HorizontalModel> horizontalModels = new ArrayList<>();
        horizontalModels.add(new HorizontalModel("Horizontal 1", "Horizontal 1", R.drawable.brave));
        horizontalModels.add(new HorizontalModel("Horizontal 2", "Horizontal 2", R.drawable.brave));
        horizontalModels.add(new HorizontalModel("Horizontal 3", "Horizontal 3", R.drawable.brave));
        horizontalModels.add(new HorizontalModel("Horizontal 4", "Horizontal 4", R.drawable.brave));
        horizontalModels.add(new HorizontalModel("Horizontal 5", "Horizontal 5", R.drawable.brave));
        return horizontalModels;
    }
}