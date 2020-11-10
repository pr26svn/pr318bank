package com.example.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public Dialog onCreateDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.login_dialog,null));

        builder.setPositiveButton(R.string.LogInButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton(R.string.CancelButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
    public void logIn_Click(View view){
        onCreateDialog();
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