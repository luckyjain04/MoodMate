package com.example.mood;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JournalActivity extends AppCompatActivity {

    EditText etJournal;
    Button btnSaveEntry;
    ListView lvJournal;
    DatabaseHelper db;
    String username;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_journal);

        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        if (username == null) username = "guest";

        etJournal = findViewById(R.id.etJournal);
        btnSaveEntry = findViewById(R.id.btnSaveEntry);
        lvJournal = findViewById(R.id.lvJournal);

        refreshList();

        btnSaveEntry.setOnClickListener(v -> {
            String entry = etJournal.getText().toString().trim();
            if (entry.isEmpty()) { Toast.makeText(this, "Write something", Toast.LENGTH_SHORT).show(); return; }
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            boolean ok = db.addJournalEntry(username, entry, date);
            if (ok) {
                etJournal.setText("");
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                refreshList();
            } else {
                Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshList() {
        List<String> list = db.getAllJournalEntries(username);
        if (list.isEmpty()) list.add("No journal entries yet ✍️");
        lvJournal.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
    }
}
