package com.example.mood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText etUser, etPass;
    Button btnRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String u = etUser.getText().toString().trim();
            String p = etPass.getText().toString().trim();
            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean ok = db.registerUser(u, p);
            if (ok) {
                Toast.makeText(this, "Registered successfully. Please login.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "User exists or error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
