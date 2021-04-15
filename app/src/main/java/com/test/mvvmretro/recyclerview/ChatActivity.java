package com.test.mvvmretro.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.test.mvvmretro.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        RecyclerView recyclerView = findViewById(R.id.rv_chat);


        List<Chat> msgList = fetchMsg();
        ChatAdapter adapter = new ChatAdapter(msgList);
        recyclerView.setAdapter(adapter);
    }

    private List<Chat> fetchMsg() {
        List<Chat> dataList = new ArrayList<>();
        boolean isMe;
        for (int i = 0; i < 100; i++) {
            if (i % 5 == 0) {
                isMe = true;
            } else {
                isMe = false;
            }
            Chat chat = new Chat("Message" + i, isMe);
            dataList.add(chat);
        }
        return dataList;
    }
}