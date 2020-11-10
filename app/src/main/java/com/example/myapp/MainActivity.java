package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.date);
        mainDate.setText(dateText);
    }

}