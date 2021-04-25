package com.test.mvvmretro.recyclerview.heterogeneous;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;

import java.util.ArrayList;

import static com.test.mvvmretro.recyclerview.heterogeneous.MainActivity.getHorizontalData;
import static com.test.mvvmretro.recyclerview.heterogeneous.MainActivity.getVerticalData;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Object> dataList;
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public MainAdapter(Context context, ArrayList<Object> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case VERTICAL:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_layout, parent, false);
                return new VerticalVH(view);

            case HORIZONTAL:
                View horizontalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_horizontal, parent, false);
                return new HorizontalVH(horizontalView);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VERTICAL) {
            verticalView((VerticalVH) holder);
        } else if (holder.getItemViewType() == HORIZONTAL) {
            horizontalView((HorizontalVH) holder);
        }
    }

    private void verticalView(VerticalVH holder) {
        VerticalAdapter verticalAdapter = new VerticalAdapter(getVerticalData());
        holder.recyclerView.setAdapter(verticalAdapter);

    }

    private void horizontalView(HorizontalVH holder) {
        HorizontalAdapter hAdapter = new HorizontalAdapter(getHorizontalData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(hAdapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (dataList.get(position) instanceof VerticalModel) {
            return VERTICAL;
        }
        if (dataList.get(position) instanceof HorizontalModel) {
            return HORIZONTAL;
        }

        return 0;
    }

    static class VerticalVH extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public VerticalVH(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_vertical);
        }
    }

    static class HorizontalVH extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;


        public HorizontalVH(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_horizontal);
        }
    }

}
