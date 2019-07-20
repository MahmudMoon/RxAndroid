package com.example.moon.rxandroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GetData {
    private static final String TAG = "GetData";
    public static List<Task> GetAllData(){
        List<Task> allTasks = new ArrayList<>();
        allTasks.add(new Task(1,"A","aaaaa"));
        allTasks.add(new Task(2,"B","bbbbbb"));
        allTasks.add(new Task(3,"C","cccccc"));
        allTasks.add(new Task(4,"D","ddddd"));
        allTasks.add(new Task(5,"E","eeeeee"));
        allTasks.add(new Task(6,"F","fffff"));
        allTasks.add(new Task(7,"G","ggggggg"));
        allTasks.add(new Task(8,"H","hhhhh"));
        allTasks.add(new Task(9,"I","iiiiii"));
        allTasks.add(new Task(10,"J","jjjjjj"));
        return allTasks;
    }


}
