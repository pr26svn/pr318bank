package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Banks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Banks.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ListView listView;
        listView = findViewById(R.id.listView);
        ArrayList<bba> arrayList = new ArrayList<>();

        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new bba( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));

        BanksAdapter banksAdapter = new BanksAdapter(this,R.layout.vvv,arrayList);

        listView.setAdapter(banksAdapter);

    }
}