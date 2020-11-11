package com.example.mobileBank;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    android.widget.TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = findViewById(R.id.txtDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        textViewDate.setText(dateFormat.format(new Date()));
    }

    //метод нажатия на кнопку "Отделения и банкоматы"
    public void branchesAndATMs_Click(View view) {
        Intent intent = new Intent(MainActivity.this, AtmActivity.class);
        startActivity(intent);
    }

    //метод нажатия на кнопку "Курсы валют"
    public void exchangeRates_Click(View view) {
        Intent intent = new Intent(MainActivity.this, ExchangeRatesActivity.class);
        startActivity(intent);
    }

    //метод нажатия на кнопку "Войти" и вызова диалогового окна авторизации
    public void signin_Click(View view) {
        final EditText editText = findViewById(R.id.username);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.DialogTheme));
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        builder.setCancelable(false);

        builder.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {

            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
}