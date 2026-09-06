package com.example.mood;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MoodListActivity extends AppCompatActivity {

    ListView lv;
    DatabaseHelper db;
    String username;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_mood_list);

        lv = findViewById(R.id.lvMoods);
        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");
        if (username == null) username = "guest";

        List<String> data = db.getAllMoods(username);
        if (data.isEmpty()) data.add("No moods recorded yet 😔");
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
    }
}
