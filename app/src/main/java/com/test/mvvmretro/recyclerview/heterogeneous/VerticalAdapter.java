package com.test.mvvmretro.recyclerview.heterogeneous;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyView> {
    private ArrayList<VerticalModel> dataList;

    public VerticalAdapter(ArrayList<VerticalModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public VerticalAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_vertical, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalAdapter.MyView holder, int position) {
        VerticalModel model = dataList.get(position);
        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDesc());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView desc;

        public MyView(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
        }
    }
}
