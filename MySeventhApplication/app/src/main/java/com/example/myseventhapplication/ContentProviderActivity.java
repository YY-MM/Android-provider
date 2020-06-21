package com.example.myseventhapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class ContentProviderActivity extends AppCompatActivity {

    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        dbHelper=new MyDatabaseHelper(this,"PhoneNumber.db",null,1);
    }
}
