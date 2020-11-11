package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Atms extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atms);

        listView = findViewById(R.id.list);

        ArrayList<AtmsBuildings> arrayList = new ArrayList<>();

        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));
        arrayList.add(new AtmsBuildings("Москва, ул. Вавилова, д. 7", "Отделение",
                "Работает", "Часы работы 00:00-00:00"));


        AtmsAdapter atmsAdapter = new AtmsAdapter(this, R.layout.list_row_atms, arrayList);

        listView.setAdapter(atmsAdapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}