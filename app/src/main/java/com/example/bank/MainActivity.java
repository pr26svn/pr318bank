package com.example.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //Обработка нажатия по кнопке "Отделения и банкоматы"
    public void banks_atm_click(View view){
        Intent intent = new Intent(MainActivity.this, Banks_and_ATMs.class);
        startActivity(intent);
    }

    //Обработка нажатия по кнопке "Курсы валют"
    public void exchange_rates_click(View view){
        Intent intent = new Intent(MainActivity.this, Exchange_rates.class);
        startActivity(intent);
    }


    //Обработка нажатия по кнопке "Вход"
    public void logIn_Click(View view){
        final EditText Et = findViewById(R.id.editTextTextPersonName);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.login_dialog,null));
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.LogInButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Et == null){
                    Toast.makeText(getApplicationContext(),"Ошибка. Вы ничего не ввели",Toast.LENGTH_SHORT);
                }
            }
        });
        builder.setNegativeButton(R.string.CancelButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Вывод даты системы
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        TextView Date_txt = findViewById(R.id.textView4);
        Date_txt.setText(format.format(new Date()));



        //Вывод курса валют
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                //Создание URL
                URL website_end_point = null;
                try {
                    website_end_point = new URL("http://api.areas.su");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                //Создание подключения
                try {
                    HttpURLConnection myConnection = (HttpURLConnection) website_end_point.openConnection();
                    myConnection.setRequestProperty("User-Agent","pr318bank");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}