package com.shanto.simpletodolistapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.shanto.simpletodolistapp.R;
import com.shanto.simpletodolistapp.adapter.TaskAdapter;
import com.shanto.simpletodolistapp.database.DatabaseClient;
import com.shanto.simpletodolistapp.database.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MaterialButton btnAdd;

    private List<Task> taskList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadTasks();
    }

    private void loadTasks() {

        taskList = DatabaseClient.getInstance(this)
                .getAppDatabase()
                .taskDao()
                .getAllTasks();

        adapter = new TaskAdapter(this, taskList);

        recyclerView.setAdapter(adapter);

    }

}