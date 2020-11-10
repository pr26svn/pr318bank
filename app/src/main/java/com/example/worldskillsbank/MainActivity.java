package com.example.worldskillsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        tv2 = findViewById(R.id.textView2);
        tv2.setText(dateFormat.format(new Date()));

        AsyncTask.execute(new Runnable() {
            @Override public void run() {
                // All your networking logic // should be here
            }
        });
        URL url;
        Dialog dialog = new Dialog(MainActivity.this);
        try {
            url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + new Date());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
        } catch (Exception e) {
            // Установите заголовок
            dialog.setTitle("Ошибка");
            // Передайте ссылку на разметку
            dialog.setContentView(R.layout.dialog_view);
            // Найдите элемент TextView внутри вашей разметки
            // и установите ему соответствующий текст
            TextView text = (TextView) dialog.findViewById(R.id.textView5);
            text.setText(e.getMessage());
            dialog.show();
        }
    }
}