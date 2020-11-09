package com.example.bank2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ATM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_t_m);
    }

    public void onClickHome(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}