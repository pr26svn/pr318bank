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

    String toast_result;

    ImageButton btn_back;
    private static final String TAG = "myLogs";
    ArrayList<Otdelenie> otdelenies = new ArrayList<Otdelenie>();
    BoxAdapter boxAdapter;

    Otdelenie otd = new Otdelenie("г. Москва, ул. Вавилова, д. 4", "00:00-00:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otdelenia);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        boxAdapter = new BoxAdapter(this, otdelenies);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);


        //otdelenies.add(new Otdelenie("г. Омск", "08:00-20:00"));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String query = "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";

                HttpsURLConnection connection = null;
                try {
                    Log.d(TAG, "Создаю подключение...");
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
                        Log.d(TAG, "JSON = " + response);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                try {

                                    Parsing(response.toString());
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





    @Override
    public void onClick(View v) {
        finish();
    }



    private void Parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        Object obj = new JSONParser().parse(file_for_parsing);
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;

        //org.json.simple.JSONObject tw = (org.json.simple.JSONObject) jo.get("mon");

        JSONArray devices = (JSONArray) jo.get("devices");

        Iterator devices_itr = devices.iterator();


        while (devices_itr.hasNext()) {
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();


            JSONObject tw = (JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            timetable = tw.get("mon").toString();


            Otdelenie otdelenie = new Otdelenie(adress_obj.get("fullAddressRu").toString(), timetable);
            otdelenies.add(otdelenie);
        }
    }
}