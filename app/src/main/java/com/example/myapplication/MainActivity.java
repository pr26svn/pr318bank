package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Date date;
    TextView mainDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.textView5);
        mainDate.setText(dateText);
    }


    public void OnClickBankomats(View view) {
        Intent intent = new Intent(this, secondActivity.class);
        startActivity(intent);
    }

    public void OnClickCurrency(View view) {
        Intent intent = new Intent(this, firstActivity.class);
        startActivity(intent);
    }
}