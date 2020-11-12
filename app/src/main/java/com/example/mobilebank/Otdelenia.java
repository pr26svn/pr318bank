package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Otdelenia extends AppCompatActivity implements View.OnClickListener {

    //Объявляю элементы интерфейса
    ImageButton btn_back;
    ListView lvMain;
    //Адаптер для кастомного ListView
    BoxAdapter boxAdapter;

    // тэг для Logcat
    private static final String TAG = "myLogs";

    //мой список отделений, в дальнейшем буду сюда добавлять объекты "Отделение"
    ArrayList<Otdelenie> otdelenies = new ArrayList<Otdelenie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otdelenia);


        //инициализирую элементы интерфейса и объекты
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        boxAdapter = new BoxAdapter(this, otdelenies);
        lvMain = (ListView) findViewById(R.id.lvMain);

        // код для фонового выполнения
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // URL в котором лежи JSON
                String query = "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";

                HttpsURLConnection connection = null;
                try {
                    //объявляю HTTPS соединение
                    Log.d(TAG, "Создаю подключение...");
                    //открываю соединение
                    connection = (HttpsURLConnection) new URL(query).openConnection();

                    // для ясности добавил, что нужно использовать GET
                    // но он и так стоит по умолчанию
                    connection.setRequestMethod("GET");

                    //установил заголовок, чтобы сервер понимал, кто к нему обращается
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    //начинаю соединение
                    connection.connect();


                    // проверка на успешность соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){
                        //объявляют reader, который будет читать мой XMl и передаю кодировку ch1251(иначе будут знаки вопроса за место русских букв)
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        //вспомогательная переменная
                        String inputLine;

                        // аналог StringBuilder из Currency.java
                        StringBuffer response = new StringBuffer();
                        while((inputLine = in.readLine()) != null){
                            response.append(inputLine);
                        }

                        in.close();


                        // просто так передать данные ListView не получится
                        // это нельзя делать в основном потоке
                        // поэтому использую метод ниже
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    //метод для парсинга JSON
                                    Parsing(response.toString());

                                    //передаю данные из JSON в адаптер, а он в ListView
                                    lvMain.setAdapter(boxAdapter);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }else{
                        Log.d(TAG, "error");
                    }

                } catch (Throwable cause){
                    cause.printStackTrace();
                }finally {

                    // отключаемся
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });

        //обработчик нажатия по элементу списка
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_for_otdelenia,
                        (ViewGroup) findViewById(R.id.custom_toast_container));


                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });


    }





    @Override
    public void onClick(View v) {
        finish();
    }




    //метод для парсинга

    // в параметры передаю строку JSON
    private void Parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        /*
         * Для парсинга использую JSON-SIMPLE
         * скачал его с интернета
         */
        Object obj = new JSONParser().parse(file_for_parsing);
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;

        // инициализирую объект devices
        // так как в нем, в JSON хранится вся инфа
        JSONArray devices = (JSONArray) jo.get("devices");

        //итератор для цикла
        Iterator devices_itr = devices.iterator();


        while (devices_itr.hasNext()) {
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();


            JSONObject tw = (JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            timetable = tw.get("mon").toString();

            //создаем otdelenie, а в параметры передаем атрибуты из JSON
            Otdelenie otdelenie = new Otdelenie(adress_obj.get("fullAddressRu").toString(), timetable);

            // добавляем в общий список
            otdelenies.add(otdelenie);
        }
    }
}