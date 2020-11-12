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

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class Otdelenia extends AppCompatActivity implements View.OnClickListener {

    String toast_result;

    ImageButton btn_back;
    private static final String TAG = "myLogs";
    ArrayList<Otdelenie> otdelenies = new ArrayList<Otdelenie>();
    BoxAdapter boxAdapter;

    Otdelenie otd = new Otdelenie("г. Москва, ул. Вавилова, д. 4", "00:00-00:00", "Работает", "Банкомат");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otdelenia);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, otdelenies);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);

        otdelenies.add(new Otdelenie("г. Омск", "08:00-20:00", "Не работает", "Отделение"));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String query = "https://api.areas.su/";

                HttpsURLConnection connection = null;
                try {
                    Log.d(TAG, "Создаю подключение...");
                    connection = (HttpsURLConnection) new URL(query).openConnection();

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){
                        Log.d(TAG, "Подключение создано успешно!!!");
                        InputStream responseBody = connection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");

                        JsonReader jsonReader = new JsonReader(responseBodyReader);

                        jsonReader.beginObject();

                        while (jsonReader.hasNext()) { // Loop through all keys
                            Log.d(TAG, "Начинаю извлечение строк из json");
                            String value = jsonReader.nextString();
                            Log.d(TAG, "Value " +  value);
                        }


                        jsonReader.close();

                        connection.disconnect();
                        Log.d(TAG, "Конец");
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                try {

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
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });


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

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 19; i++) {
            otdelenies.add(otd);
        }
    }



    @Override
    public void onClick(View v) {
        finish();
    }
}