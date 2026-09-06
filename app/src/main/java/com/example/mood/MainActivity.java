package com.example.mood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnView, btnJournal, btnLogout;
    String username;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("username");
        if (username == null) username = "guest";

        btnAdd = findViewById(R.id.btnAddMood);
        btnView = findViewById(R.id.btnViewMoods);
        btnJournal = findViewById(R.id.btnJournal);
        btnLogout = findViewById(R.id.btnLogout);

        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddMoodActivity.class);
            i.putExtra("username", username);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnView.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MoodListActivity.class);
            i.putExtra("username", username);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnJournal.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, JournalActivity.class);
            i.putExtra("username", username);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnLogout.setOnClickListener(v -> {
            finish();
        });
    }
}
