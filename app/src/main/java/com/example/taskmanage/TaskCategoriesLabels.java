package com.example.taskmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.taskmanage.modelcalss.category;
import com.example.taskmanage.modelcalss.recycleAdapter;

import java.util.ArrayList;

public class TaskCategoriesLabels extends AppCompatActivity {


    private ArrayList<category> categories;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_categories_labels);
        recyclerView= findViewById(R.id.recycleView);
        categories = new ArrayList<>();

        setUserInfo();
        recycleAdapter adapter = new recycleAdapter(categories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserInfo() {
        categories.add(new category("1234","work"));
        categories.add(new category("12345","personal"));
        categories.add(new category("12234","shopping"));
    }
}