package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Banks_and_ATMs extends AppCompatActivity {

    public void return_click(View view){
        Intent intent = new Intent(Banks_and_ATMs.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_and__a_t_ms);
    }
}