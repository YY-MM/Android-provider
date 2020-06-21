package com.example.myseventh_2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button query=(Button)findViewById(R.id.queryall);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "按下按钮", Toast.LENGTH_SHORT).show();
                Uri uri= Uri.parse("content://com.example.myseventhapplication.provider/p_number");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                    Toast.makeText(MainActivity.this, "表不为空", Toast.LENGTH_SHORT).show();
                    while(cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String number=cursor.getString(cursor.getColumnIndex("number"));
                        String sex=cursor.getString(cursor.getColumnIndex("sex"));
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity","name is"+name);
                        Log.d("MainActivity","number is"+number);
                        Log.d("MainActivity","sex is"+sex);
                    }
                    cursor.close();
                }
            }
        });

       Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("content://com.example.myseventhapplication.provider/p_number");
                ContentValues values = new ContentValues();
                values.put("sort","1");
                values.put("name","张三");
                values.put("number","11111235111");
                Uri newUri = getContentResolver().insert(uri,values);
                Log.d("我插入了什么？","是："+newUri);
                String newId = newUri.getPathSegments().get(1);
            }
        });

    }
}
