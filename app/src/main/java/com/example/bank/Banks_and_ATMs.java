package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Banks_and_ATMs extends AppCompatActivity {

    public void return_click(View view){
        Intent intent = new Intent(Banks_and_ATMs.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_and__a_t_ms);

        ListView listView = findViewById(R.id.list_view);
        ArrayList<atms_and_banks>arrayList = new ArrayList<>();

        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new atms_and_banks( "Москва", "Банкомат", "Работает", "09:00-20:00" ));

        adapter_bank_atm adapter = new adapter_bank_atm(this, R.layout.list, arrayList);

        listView.setAdapter(adapter);
    }
}