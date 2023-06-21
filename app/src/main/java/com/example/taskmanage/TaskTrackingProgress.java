package com.example.taskmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.taskmanage.modelcalss.category;
import com.example.taskmanage.modelcalss.progrclass;
import com.example.taskmanage.modelcalss.recycleAdapter;
import com.example.taskmanage.modelcalss.recycleViewprogrss;

import java.util.ArrayList;

public class TaskTrackingProgress extends AppCompatActivity {

    private ArrayList<progrclass> progrclasses;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_tracking_progress);

        recyclerView = findViewById(R.id.recycleView2);
        progrclasses = new ArrayList<>();

        setUserInfo();
        recycleViewprogrss adapter = new recycleViewprogrss(progrclasses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserInfo() {
        progrclasses.add(new progrclass("Name1", 10));
        progrclasses.add(new progrclass("Name2", 75));
        progrclasses.add(new progrclass("Name3", 100));
    }

}