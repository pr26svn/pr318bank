package com.example.mobileBank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = findViewById(R.id.txtDate);

        //добавление даты на стартовый экран
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        textViewDate.setText(dateFormat.format(new Date()));
    }

    //метод нажатия на кнопку "Отделения и банкоматы"
    public void branchesAndATMs_Click(View view) {
        Intent intent = new Intent(MainActivity.this, BankActivity.class);
        startActivity(intent);
    }

    //метод нажатия на кнопку "Курсы валют"
    public void exchangeRates_Click(View view) {
        Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
        startActivity(intent);
    }

    //метод нажатия на кнопку "Войти" и вызова диалогового окна авторизации
    public void signin_Click(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.DialogTheme));
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        builder.setCancelable(false);

        //кнопка Войти
        builder.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {

            }
        });

        //кнопка Отмена
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
}