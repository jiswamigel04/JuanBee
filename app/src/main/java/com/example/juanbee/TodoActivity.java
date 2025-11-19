package com.example.juanbee;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {

    Button btnBack, btnAddTask;
    EditText etTask;
    ListView lvTasks;

    ArrayList<String> taskList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_todo);

        btnBack = findViewById(R.id.btnBack);
        btnAddTask = findViewById(R.id.btnAddTask);
        etTask = findViewById(R.id.etTask);
        lvTasks = findViewById(R.id.lvTasks);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lvTasks.setAdapter(adapter);

        // Go back to Home
        btnBack.setOnClickListener(v -> finish());

        // Add Task
        btnAddTask.setOnClickListener(v -> {
            String task = etTask.getText().toString().trim();

            if (task.isEmpty()) {
                Toast.makeText(this, "Please enter a task!", Toast.LENGTH_SHORT).show();
                return;
            }

            taskList.add("📝 " + task);
            adapter.notifyDataSetChanged();
            etTask.setText("");
        });

        // Long press to delete
        lvTasks.setOnItemLongClickListener((parent, view, position, id) -> {
            taskList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
