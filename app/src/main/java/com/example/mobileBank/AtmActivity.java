package com.example.mobileBank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AtmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);
    }

    public void returnBack_Click(View view) {
        Intent intent = new Intent(AtmActivity.this, MainActivity.class);
        startActivity(intent);
    }
}