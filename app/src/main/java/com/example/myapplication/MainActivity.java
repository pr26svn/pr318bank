package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String one = "ssss";
        String two = "aaaa";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View a = findViewById(R.id.button5);

    }
}