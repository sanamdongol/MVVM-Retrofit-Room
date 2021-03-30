package com.test.mvvmretro.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.test.mvvmretro.R;
import com.test.mvvmretro.data.db.Todos;

import java.util.ArrayList;
import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private List<Todos> todoList = new ArrayList<>();

    @NonNull
    @Override
    public TodosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodosAdapter.ViewHolder holder, int position) {
        Todos todo = todoList.get(position);
        holder.tvTitle.setText(todo.getTitle());
        holder.tvDescription.setText(todo.getId());
        Picasso.get().load(todo.getAvatar()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void addTodoList(List<Todos> todos) {
        this.todoList = todos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            img = itemView.findViewById(R.id.img);
        }
    }
}
