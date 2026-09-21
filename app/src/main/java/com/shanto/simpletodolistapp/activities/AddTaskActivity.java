package com.shanto.simpletodolistapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shanto.simpletodolistapp.R;
import com.shanto.simpletodolistapp.database.DatabaseClient;
import com.shanto.simpletodolistapp.database.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Enter Title");
                return;
            }

            Task task = new Task(title, description);

            DatabaseClient.getInstance(this)
                    .getAppDatabase()
                    .taskDao()
                    .insert(task);

            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show();

            finish();

        });

    }
}