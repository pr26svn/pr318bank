package com.example.worldskillsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    TextView tv2;
    Button b1;
    Intent intent;
    Date date;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        tv2 = findViewById(R.id.textView2);
        tv2.setText(dateFormat.format(date));

        b1 = findViewById(R.id.button1);

        dialog = new Dialog(MainActivity.this);
    }

    public void onClick_button1(View view){
        intent = new Intent(this, otdandban.class);
        startActivity(intent);
    }

    public void onClick_button2(View view){
        intent = new Intent(this, kurval.class);
        startActivity(intent);
    }

    public void onClick_button3(View view){
        dialog.setContentView(R.layout.activity_login);
        dialog.show();
    }
}