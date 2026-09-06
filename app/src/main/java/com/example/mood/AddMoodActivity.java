package com.example.mood;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class AddMoodActivity extends AppCompatActivity {

    Spinner spinnerMood;
    EditText etNote;
    Button btnDate, btnSave;
    DatabaseHelper db;
    String username;
    String selectedDate;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_add_mood);

        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        if (username == null) username = "guest";

        spinnerMood = findViewById(R.id.spinnerMood);
        etNote = findViewById(R.id.etNote);
        btnDate = findViewById(R.id.btnDate);
        btnSave = findViewById(R.id.btnSave);

        String[] moods = {"😊 Happy", "😔 Sad", "🤩 Excited", "😌 Calm", "😡 Angry", "🙂 Okay"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMood.setAdapter(adapter);

        Calendar cal = Calendar.getInstance();
        selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d",
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
        btnDate.setText("Date: " + selectedDate);

        btnDate.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            new DatePickerDialog(AddMoodActivity.this, (view, y, m, d) -> {
                m = m + 1;
                selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", y, m, d);
                btnDate.setText("Date: " + selectedDate);
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnSave.setOnClickListener(v -> {
            String mood = spinnerMood.getSelectedItem().toString();
            String note = etNote.getText().toString().trim();
            boolean ok = db.insertMood(username, mood, note, selectedDate);
            if (ok) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
