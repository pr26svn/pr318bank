package com.example.banc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchBank extends AppCompatActivity {

    Button main_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branches_and_atms);

        main_screen = findViewById(R.id.button9);

        main_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchBank.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}