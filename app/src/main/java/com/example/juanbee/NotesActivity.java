package com.example.juanbee;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    Button btnAddNote, btnSave;
    LinearLayout addNoteLayout;
    EditText etNoteTitle, etNoteContent;
    ListView lvNotes;

    ArrayList<String> notesList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide action bar
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_notes);

        btnAddNote = findViewById(R.id.btnAddNote);
        btnSave = findViewById(R.id.btnSave);
        addNoteLayout = findViewById(R.id.addNoteLayout);
        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        lvNotes = findViewById(R.id.lvNotes);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvNotes.setAdapter(adapter);

        // Show Add Note form
        btnAddNote.setOnClickListener(v -> {
            addNoteLayout.setVisibility(View.VISIBLE);
        });

        // Save Note
        btnSave.setOnClickListener(v -> {
            String title = etNoteTitle.getText().toString().trim();
            String content = etNoteContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            notesList.add("📌 " + title + "\n" + content);
            adapter.notifyDataSetChanged();

            etNoteTitle.setText("");
            etNoteContent.setText("");
            addNoteLayout.setVisibility(View.GONE);
        });

        // Long press to delete
        lvNotes.setOnItemLongClickListener((parent, view, position, id) -> {
            notesList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
