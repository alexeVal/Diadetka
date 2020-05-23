package com.example.myapplication;

public class VK_ID  {
    private int id;
    private int User_id;

    public VK_ID(int user_id) {
        User_id = user_id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }
}
