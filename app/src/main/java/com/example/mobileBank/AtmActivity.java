package com.example.mobileBank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class AtmActivity extends AppCompatActivity {

    //!!!Тестирование адаптера, запросы и парсинг еще не реализоавны!!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);

        ListView listView;
        listView = findViewById(R.id.listViewAtms);
        ArrayList<Bank> arrayList = new ArrayList<>();

        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Закрыт", "09:00-20:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Закрыт", "09:00-20:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Закрыт", "09:00-20:00" ));

        BankAdapter bankAdapter = new BankAdapter(this, R.layout.list_item_atm, arrayList);

        listView.setAdapter(bankAdapter);

    }

    //метод нажатия на кнопку "Главное меню"
    public void returnBack_Click(View view) {
        Intent intent = new Intent(AtmActivity.this, MainActivity.class);
        startActivity(intent);
    }
}