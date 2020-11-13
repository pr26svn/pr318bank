package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class a_t_ms extends AppCompatActivity implements View.OnClickListener {
    String toast_result;
    LinearLayout btn_back;
    private static final String TAG = "myLogs";
    ArrayList<a_t_m> otdeleniya = new ArrayList<a_t_m>();
    ATMAdapter atmAdapter;
    a_t_m otd = new a_t_m("г. Москва, ул. Вавилова, д. 4", "00:00-00:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_t_ms);
        btn_back = (LinearLayout) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        atmAdapter = new ATMAdapter(this, otdeleniya);
        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
//запуск асинхронного метода
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String query = "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";
                HttpsURLConnection connection = null;
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    connection.connect();
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){

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
                                    //запуск парсинга и адаптера
                                    Parsing(response.toString());
                                    lvMain.setAdapter(atmAdapter);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }else{
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

//окно со статусом отделения
     /*   lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_for_otdelenia, (ViewGroup) findViewById(R.id.custom_toast_container));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

      */
    }
    @Override
    public void onClick(View v) {
        finish();
    }
    private void Parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        Object obj = new JSONParser().parse(file_for_parsing);
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;
        JSONArray devices = (JSONArray) jo.get("devices");
        Iterator devices_itr = devices.iterator();
        while (devices_itr.hasNext()) {
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();

            JSONObject tw = (JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            timetable = tw.get("mon").toString();

            a_t_m otdelenie = new a_t_m(adress_obj.get("fullAddressRu").toString(), timetable);
            otdeleniya.add(otdelenie);
        }
    }
}