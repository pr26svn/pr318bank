package com.example.mobileBank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AtmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);

        ListView listView;
        listView = findViewById(R.id.listViewAtms);
        ArrayList<Banks> arrayList = new ArrayList<>();

        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));

        BanksAdapter banksAdapter = new BanksAdapter(this, R.layout.list_row_atm, arrayList);

        listView.setAdapter(banksAdapter);

    }

    public void returnBack_Click(View view) {
        Intent intent = new Intent(AtmActivity.this, MainActivity.class);
        startActivity(intent);
    }
}