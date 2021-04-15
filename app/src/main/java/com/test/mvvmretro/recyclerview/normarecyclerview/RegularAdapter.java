package com.test.mvvmretro.recyclerview.normarecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;

import java.util.List;

public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {
    private List<MyRegularData> data;

    public RegularAdapter(List<MyRegularData> data) {

        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_regular_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyRegularData model = data.get(position);
        holder.tvTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
