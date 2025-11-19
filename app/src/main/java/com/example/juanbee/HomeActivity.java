package com.example.juanbee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout syllabusCard, pomodoroCard, notesCard, calendarCard, todoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Action Bar if showing
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_home);

        // Match IDs from your XML
        syllabusCard = findViewById(R.id.syllabusCard);
        pomodoroCard = findViewById(R.id.pomodoroCard);
        notesCard = findViewById(R.id.notesCard);
        calendarCard = findViewById(R.id.calendarCard);
        todoCard = findViewById(R.id.todoCard);

        // Syllabus Activity
        syllabusCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SyllabusActivity.class);
            startActivity(intent);
        });

        // Pomodoro Activity
        pomodoroCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PomodoroActivity.class);
            startActivity(intent);
        });

        // Notes Activity
        notesCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NotesActivity.class);
            startActivity(intent);
        });

        // Calendar Activity
        calendarCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CalendarActivity.class);
            startActivity(intent);
        });

        // To-do List Activity
        todoCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TodoActivity.class);
            startActivity(intent);
        });
    }
}
