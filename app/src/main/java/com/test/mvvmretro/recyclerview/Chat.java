package com.test.mvvmretro.recyclerview;

public class Chat {

    String msg;
    boolean isMe;

    public Chat(String msg, boolean isMe) {
        this.msg = msg;
        this.isMe = isMe;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
