package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.strictmode.CredentialProtectedWhileLockedViolation;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

public class changeBank extends AppCompatActivity {

    private Button buttonBack; // <- кнопка возврата
    public ArrayList<String> banksArray; // <- список банков для listView
    private ArrayAdapter<String> adapter; // <- адаптер для listView
    private ListView listView; // <- список для отображения банков и терминалов
    private String text; // <- json код

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bank);

        // инициализация кнопки
        buttonBack = (Button) findViewById(R.id.button_4);
        // переход на главный экран
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(changeBank.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // инициализация списка банков
        banksArray = new ArrayList<String>();

        // инициализация адаптера
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_modify_item, banksArray);
        // инициализация listView
        listView = (ListView) findViewById(R.id.listView_1);

        // создание фонового потока
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // выгрузка json файлв
                    text = downloadJSONFile();
                } finally {

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // парсинг json файлв
                            parsingJSONFiles(text);
                            // установка адаптера на listView
                            listView.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public String downloadJSONFile() {
        HttpURLConnection connection = null;
        StringBuilder builder = null;
        try {
            // установка соединения с сайтом, хранящий json файл
            connection = (HttpURLConnection) new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=").openConnection();

            connection.setRequestMethod("getJSONFile"); // <- установка метода запроса
            connection.setRequestProperty("User-Agent", "my-rest-app-v0.1"); // <- установка агента
            connection.setUseCaches(false); // <- отключение использования кэша
            connection.setConnectTimeout(250); // <- время подключения
            connection.setReadTimeout(250); // <- время чтения

            connection.connect(); // <- поключение

            builder = new StringBuilder(); // переменая создания строки
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) { // <- проверка на подключение
                // считывание данных
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line; // строка для чтения
                // цикл чтения данных
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect(); // <- отключение
            }
        }

        return builder.toString(); // возращаем полученные данные в формате string
    }

    private void parsingJSONFiles(String filePath) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        Object obj = null;
        try {
            obj = new JSONParser().parse(filePath); // <- парсим полученный код
        } catch (ParseException e) {
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
        // инициализируем объект, хранаящий информацию о JSON
        org.json.simple.JSONArray devices = (org.json.simple.JSONArray) jo.get("devices");
        // инициализируем итератор
        Iterator devices_itr = devices.iterator();
        // считываем данные
        while (devices_itr.hasNext()) {
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();

            // считываем время работы банка
            JSONObject tw = (JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            try {
                timetable = tw.get("mon").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // создаем объект класса BankTerminals, который хранит информацию об отделении или терминале
            BankTerminals bt = new BankTerminals(adress_obj.get("fullAddressRu").toString(), timetable);
            banksArray.add(bt.getBankData()); // <- добавляем эту информацию в список банков
        }
    }
}