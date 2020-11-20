package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
public class a_t_ms extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btn_back;
    ListView lvMain;
    //Адаптер для кастомного ListView
    ATMAdapter atmAdapter;

    //Список объектов a_t_m
    ArrayList<a_t_m> otdeleniya = new ArrayList<a_t_m>();
    String dayOfTheWeek, curr_time,
           // curr_date,
            result_date;
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    SimpleDateFormat sdf_time = new SimpleDateFormat("kk:mm");
    SimpleDateFormat full_format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    Date data = new Date();
        // Date ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_t_ms);
//инициализирую элементы интерфейса и объекты
        dayOfTheWeek = sdf.format(data);
        curr_time = sdf_time.format(data);
        result_date = full_format.format(data);
        btn_back = (LinearLayout) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        atmAdapter = new ATMAdapter(this, otdeleniya);
        lvMain = (ListView) findViewById(R.id.lvMain);

        //выполнение подкючения в фоновом режиме
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // URL в котором лежит JSON
                String query = "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=Черкассы";

                HttpsURLConnection connection = null;
                try {
                    //объявле HTTPS соединение
                    //открываю соединение
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    connection.setRequestMethod("GET");
                    //задание заголовка для идентификации
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    //начало соединение
                    connection.connect();
                    // проверка на успешность соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){
                        //объявление ридера, для чтение XMl и передачи кодировки ch1251(для руссификации)
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while((inputLine = in.readLine()) != null){
                            response.append(inputLine);
                        }
                        in.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // вызов парсинга
                                    Parsing(response.toString());
                                    //передача данных из JSON в ListView
                                    lvMain.setAdapter(atmAdapter);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else{
                    }
                    //вылавливание исключений
                } catch (Throwable cause){
                    cause.printStackTrace();
                }finally {
                    // отключение
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });
    }

    //вспомогательный метод для вычисления времени работы
    boolean formatting_date(String date_from_json, Date curr_date) throws java.text.ParseException {

        SimpleDateFormat date_format = new SimpleDateFormat("dd-M-yyyy");
        String cudate = date_format.format(data);
        String start_time = cudate + " " +  date_from_json.substring(0, 5);
        String end_time = cudate + " " +  date_from_json.substring(7);
        Date st = full_format.parse(start_time);
        Date et = full_format.parse(end_time);
        if (!(data.before(st) || data.after(et))) {
            return true;
        }else{
            return  false;
        }
    }
    @Override
    public void onClick(View v) {
        finish();
    }
    //метод парсинга
    private void Parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException, java.text.ParseException {
        //создание объекта json для считывания файла
        Object obj = new JSONParser().parse(file_for_parsing);
        //получение json объекта из файла
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
        //инициализация массива devices
        JSONArray devices = (JSONArray) jo.get("devices");
        //итератор для цикла
        Iterator devices_itr = devices.iterator();
        //переменная для времени работы
        String timetable = "";
        //цикл для прохождения по массиву devices
        while (devices_itr.hasNext()) {
            //получение объекта массива
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) devices_itr.next();
            //получение объекта "время работы"
            JSONObject days = (JSONObject) jsonObject.get("tw");
            boolean works = false;
            switch (dayOfTheWeek){
                //получение информации, работает ли данное отделение сейчас
                case "Monday":
                    timetable= days.get("mon").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
                case "Tuesday":
                    timetable= days.get("tue").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
                case "Wednesday":
                    timetable= days.get("wed").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
                case "Thursday":
                    timetable= days.get("thu").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
                case "Friday":
                    timetable= days.get("fri").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
                case "Saturday":
                    timetable= days.get("sat").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
                case "Sunday":
                    timetable= days.get("sun").toString();
                    if (formatting_date(timetable, data)){
                        works = true;
                    }else {
                        works = false;
                    }
                    break;
            }

            a_t_m bankomat = new a_t_m(jsonObject.get("fullAddressRu").toString(), timetable, works);
            // добавляем  в общий список
            otdeleniya.add(bankomat);
        }
    }

}
