package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Otdelenia extends AppCompatActivity {


    ArrayList<Otdelenie> otdelenies = new ArrayList<Otdelenie>();
    BoxAdapter boxAdapter;

    Otdelenie otd = new Otdelenie("г. Москва, ул. Вавилова, д. 4", "00:00-00:00", "Работает");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otdelenia);


        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, otdelenies);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            otdelenies.add(otd);
        }
    }


}