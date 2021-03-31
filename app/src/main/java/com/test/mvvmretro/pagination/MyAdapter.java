package com.test.mvvmretro.pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.mvvmretro.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<User> dataList = new ArrayList<>();

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        User model = dataList.get(position);
        holder.tvAuthor.setText(model.author);
        holder.tvWidth.setText(model.width);
        Picasso.get()
                .load("http://i.imgur.com/DvpvklR.png")
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<User> userList) {
        this.dataList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvAuthor;
        TextView tvWidth;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvWidth = itemView.findViewById(R.id.tv_width);

        }
    }
}
