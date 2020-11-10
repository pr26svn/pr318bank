package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

public class courses extends AppCompatActivity {

    Intent intent;
    MainActivity m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
    }

    public void onClick_Home(View view){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}