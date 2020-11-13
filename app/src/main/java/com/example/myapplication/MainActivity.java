package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    Button changeBankButton; // <- кнопка перехода в отделения и банкоматы
    Button valueCourseButton; // <- кнопка перехода на курсы и валюты
    Button signInButton; // <- кнопка входа

    TextView dateText; // <- текст для отображения даты

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // инициализация кнопок
        changeBankButton = (Button)findViewById(R.id.button_1);
        // переход на отделения и банкоматы
        changeBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, changeBank.class);
                startActivity(intent);
            }
        });
        valueCourseButton = (Button)findViewById(R.id.button_2);
        // переход на курсы и валюты
        valueCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, valueCourse.class);
                startActivity(intent);
            }
        });
        signInButton = (Button)findViewById(R.id.button_3);
        // переход на диалоговое окно входа
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = findViewById(R.id.editText_1);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); // <- создание диалогового окна
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.activity_login, null));
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.loginButtonString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText == null){
                            Toast.makeText(getApplicationContext(),"Поле для ввода пустое",Toast.LENGTH_SHORT);
                        }
                    }
                });
                builder.setNegativeButton(R.string.cancelButtonString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });

        // инициализация даты
        dateText = (TextView) findViewById(R.id.text_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateText.setText(dateFormat.format(new Date()));
    }
}