package com.test.mvvmretro.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {
    private List<Chat> msgList;
    private OnItemClickListener listener;

    public ChatAdapter(List<Chat> msgList, OnItemClickListener listener) {
        this.msgList = msgList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (msgList.get(position).isMe) {
            return 1; //msg sent
        } else {
            return 0; // msg receive
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.sent_msg, parent, false);
            return new MsgSentVH(view);
        } else {
            view = layoutInflater.inflate(R.layout.row_received, parent, false);
            return new MsgReceiveVH(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat data = msgList.get(position);
        if (getItemViewType(position) == 1) {
            MsgSentVH svh = (MsgSentVH) holder;
            svh.tvSentMsg.setText(data.getMsg());
            //setClick(holder, position);
        } else {
            MsgReceiveVH rvh = (MsgReceiveVH) holder;
            rvh.tvReceiveMsg.setText(data.getMsg());
        }
    }

    private void setClick(MsgReceiveVH holder, int position) {
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class MsgReceiveVH extends RecyclerView.ViewHolder {
        TextView tvReceiveMsg;
        ConstraintLayout rootLayout;

        public MsgReceiveVH(@NonNull View itemView) {
            super(itemView);
            tvReceiveMsg = itemView.findViewById(R.id.tv_received_msg);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }

    static class MsgSentVH extends RecyclerView.ViewHolder {
        TextView tvSentMsg;

        public MsgSentVH(@NonNull View itemView) {
            super(itemView);
            tvSentMsg = itemView.findViewById(R.id.tv_msg_sent);
        }
    }

    interface OnItemClickListener {
        void onItemClick();
    }
}
