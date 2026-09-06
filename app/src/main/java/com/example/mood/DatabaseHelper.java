package com.example.mood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "moodapp.db";
    private static final int DB_VERSION = 2;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_MOODS = "moods";
    private static final String TABLE_JOURNAL = "journal";

    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_MOOD = "mood";
    private static final String COL_NOTE = "note";
    private static final String COL_DATE = "date";
    private static final String COL_ENTRY = "entry";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE " + TABLE_USERS + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createUsers);

        String createMoods = "CREATE TABLE " + TABLE_MOODS + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_MOOD + " TEXT, " +
                COL_NOTE + " TEXT, " +
                COL_DATE + " TEXT)";
        db.execSQL(createMoods);

        String createJournal = "CREATE TABLE " + TABLE_JOURNAL + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_ENTRY + " TEXT, " +
                COL_DATE + " TEXT)";
        db.execSQL(createJournal);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOODS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        onCreate(db);
    }

    // Users
    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COL_ID},
                COL_USERNAME + "=? AND " + COL_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    // Moods
    public boolean insertMood(String username, String mood, String note, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_MOOD, mood);
        values.put(COL_NOTE, note);
        values.put(COL_DATE, date);
        long result = db.insert(TABLE_MOODS, null, values);
        return result != -1;
    }

    public List<String> getAllMoods(String username) {
        List<String> moods = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOODS, null, COL_USERNAME + "=?", new String[]{username}, null, null, COL_DATE + " DESC");
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE));
                String mood = cursor.getString(cursor.getColumnIndexOrThrow(COL_MOOD));
                String note = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOTE));
                if (note == null) note = "";
                moods.add(date + " — " + mood + (note.isEmpty() ? "" : ("\nNote: " + note)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return moods;
    }

    // Journal
    public boolean addJournalEntry(String username, String entry, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_ENTRY, entry);
        values.put(COL_DATE, date);
        long result = db.insert(TABLE_JOURNAL, null, values);
        return result != -1;
    }

    public List<String> getAllJournalEntries(String username) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_JOURNAL, null, COL_USERNAME + "=?", new String[]{username}, null, null, COL_DATE + " DESC");
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE));
                String entry = cursor.getString(cursor.getColumnIndexOrThrow(COL_ENTRY));
                list.add(date + " — " + entry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
