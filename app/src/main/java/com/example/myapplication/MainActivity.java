package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        TextView Date_txt=findViewById(R.id.textView2);
        Date_txt.setText(format.format(new Date()));
    }
}