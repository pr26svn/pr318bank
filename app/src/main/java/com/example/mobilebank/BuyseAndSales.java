package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BuyseAndSales extends AppCompatActivity {

    Date date;
    TextView mainDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyse_and_sales);

        date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dataText=dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.dateTextView);
        mainDate.setText(dataText);
    }


    public void MainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}