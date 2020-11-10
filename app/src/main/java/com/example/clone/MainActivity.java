package com.example.clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date date;

        TextView mainDate;

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.textView6);
        mainDate.setText(dateText);

    }
    public void onClickFirst(View view) {
        intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void onClickCurancy(View view) {
        intent = new Intent(this, ThirthActivity.class);
        startActivity(intent);
    }


}