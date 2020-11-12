package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class valueCourse extends AppCompatActivity {

    Button bankButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_course);

        bankButton = (Button)findViewById(R.id.button_5);
        bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(valueCourse.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}