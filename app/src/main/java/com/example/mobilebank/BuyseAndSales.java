package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.example.mobilebank.R.*;

public class BuyseAndSales extends AppCompatActivity {

    Date date;
    TextView mainDate;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_buyse_and_sales);

        date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dataText=dateFormat.format(date);
        mainDate=(TextView) findViewById(id.dateTextView);
        mainDate.setText(dataText);




        listView = findViewById(id.listViewBuyse);
        ArrayList<Sales> arrayList = new ArrayList<>();


        arrayList.add(new Sales(drawable.flag, "USD", "Американская валюта", "68", "70"));

        SalesAdapter salesAdapter = new SalesAdapter(this, layout.list_row_sales, arrayList);

        listView.setAdapter(salesAdapter);
    }


    public void MainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}