package com.example.worldskillsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    // создаем переменные
    TextView tv2, tv3, tv4;
    Button b1;
    Intent intent;
    Date date;
    Dialog dialog;
    public CurrencyXMLParser parser = new CurrencyXMLParser();
    ArrayList<Currency> currencyArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // соединяем код с дизайном
        setContentView(R.layout.activity_main);

        // получаем текущую дату
        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        // соединяем компоненты с переменными
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv2.setText(dateFormat.format(date));

        b1 = findViewById(R.id.button1);

        // создание переменной для окна диалогов
        dialog = new Dialog(MainActivity.this);

        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date.toString());
    }

    // событие нажатия кнопки "Отделения и банкоматы"
    public void onClick_button1(View view){
        intent = new Intent(this, otdandban.class);
        startActivity(intent);
    }

    // событие нажатия кнопки "Курсы валют"
    public void onClick_button2(View view){
        intent = new Intent(this, kurval.class);
        startActivity(intent);
    }

    // событие нажатия кнопки "Войти"
    public void onClick_button3(View view){
        dialog.setContentView(R.layout.activity_login);
        dialog.show();
    }

    // метод для вывода курсов на вторую кнопку
    public void  start(){
        currencyArrayList = parser.getCurrencies();
        tv3.setText(currencyArrayList.get(10).getPrice());
        tv4.setText(currencyArrayList.get(11).getPrice());
    }

    // создаем класс для парсинга
    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadFile";

        // кастомизируем элементы
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "xml document: " + s);
            if (s != null && parser.parse(s)) {
                start();
            }
        }

        // сохраняем первый элемент в списке
        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = downloadXML(strings[0]);
            } catch (IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            }
            return content;
        }

        // создаем метод для выполнения парсинга
        private String downloadXML(String urlPath) throws IOException {
            StringBuilder xmlResult = new StringBuilder();
            BufferedReader reader = null;
            try {

                // устанавливаем соединение
                URL url = new URL(urlPath);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            } catch (MalformedURLException e) {
                Log.e(TAG, "downloadXML: InvalidUrl" + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch (SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
            return null;
        }
    }
}