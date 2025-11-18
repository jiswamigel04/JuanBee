package com.example.juanbee; // 🐝 change this if your package name is different

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout syllabusCard; // we’ll detect this from your layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        syllabusCard = findViewById(R.id.syllabusCard);

        syllabusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user clicks the Syllabus card:
                Intent intent = new Intent(HomeActivity.this, SyllabusActivity.class);
                startActivity(intent);
            }
        });
    }
}
