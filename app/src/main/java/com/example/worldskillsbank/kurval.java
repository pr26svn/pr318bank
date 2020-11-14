package com.example.worldskillsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import android.content.Intent;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class kurval extends AppCompatActivity {

    // создаем переменные
    Date date;
    TextView tv6;
    Intent intent;
    ListView lv2;
    XMLAdapter XMLAdapter;
    ArrayList<Currency> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // соединяем код с дизайном
        setContentView(R.layout.activity_kurval);

        // получаем текущую дату
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        // соединяем компоненты с переменными
        tv6 = findViewById(R.id.textView6);
        tv6.setText(dateFormat.format(date));
        lv2 = findViewById(R.id.listView2);

        // заполняем ListView
        Bundle arguments = getIntent().getExtras();
        list = (ArrayList<Currency>) getIntent().getSerializableExtra("list");
        XMLAdapter = new XMLAdapter(this, list);
        lv2.setAdapter(XMLAdapter);
    }

    // выход на главную страницу
    public void onClick_button4(View view){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}