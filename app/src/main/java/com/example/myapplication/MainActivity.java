package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Date date;
    Intent intent;
    TextView mainDate;
    @Override
    //Представляет собой первоначальную настройку activity,в частности, создаются объекты визуального интерфейса
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Обновляющаяся дата
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.data);
        mainDate.setText(dateText);
    }
    //Обработчик нажатия на кнопку "Курс валют"
    public void StartKursBalut(View view) {
        Intent intent = new Intent(MainActivity.this, Valut.class);
        startActivity(intent);
    }
    //добавление кнопок на AlertDialog
    public void AlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.builder);
        final View customLayout = getLayoutInflater().inflate(R.layout.login_activity, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Войти", (dialog, which) -> dialog.cancel());
        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    //Обработчик нажатия на кнопку "Отделения и банкоматы"
    public void onBank(View view) {
        Intent intent = new Intent(MainActivity.this, Bank.class);
        startActivity(intent);
    }
}