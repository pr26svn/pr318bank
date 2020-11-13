package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

public class Atms extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    AtmsAdapter atmsAdapter;
    ArrayList<AtmsBuildings> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atms);

        listView = findViewById(R.id.list);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader;
                String line = null;
                StringBuffer responseContent = new StringBuffer();
                HttpsURLConnection connection = null;
                try{
                    URL url = new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=");
                    connection = (HttpsURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");

                    if (connection.getResponseCode() > 299){
                        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        while((line = reader.readLine()) !=null) {
                            responseContent.append(line);
                        }
                    } else {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            responseContent.append(line);
                        }
                        reader.close();
                    }


                    runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void run() {
                            try {
                                arrayList = parse(responseContent.toString());
                                atmsAdapter = new AtmsAdapter(Atms.this, R.layout.list_row_atms, arrayList);

                                listView.setAdapter(atmsAdapter);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });




    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<AtmsBuildings> parse(String responseBody) throws JSONException {
        ArrayList<AtmsBuildings> arrayList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        Date currentDate = new Date();
        c.setTime(currentDate);
        String dayOfWeek;
        switch(c.get(Calendar.DAY_OF_WEEK)){
            case 2:
                dayOfWeek = "mon";
                break;
            case 3:
                dayOfWeek = "tue";
                break;
            case 4:
                dayOfWeek = "wed";
                break;
            case 5:
                dayOfWeek = "thu";
                break;
            case 6:
                dayOfWeek = "fri";
                break;
            case 7:
                dayOfWeek = "sat";
                break;
            case 1:
                dayOfWeek = "sun";
                break;
            default:
                dayOfWeek = "none";
        }
        JSONObject response = new JSONObject(responseBody);
        JSONArray js = response.getJSONArray("devices");
        for (int i = 0; i < js.length(); i++) {
            JSONObject typeAndAddress = js.getJSONObject(i);
            JSONObject time = typeAndAddress.getJSONObject("tw");

            arrayList.add(new AtmsBuildings(typeAndAddress.getString("fullAddressRu"),
                    "Банкомат",
                    isDateState((String)time.get(dayOfWeek)),
                    "Часы работы " + time.get(dayOfWeek),
                    isDateColor((String) time.get(dayOfWeek))));

        }

        return arrayList;
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public static String isDateState(String date){
        String res = "Не работает";
        Date currentDate = new Date();
        boolean bool = false;
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String now = dateFormat.format(currentDate);
        char[] datechar = date.toCharArray();
        String dateStart = "";
        String dateEnd = "";
        for (int i = 8; i < date.length(); i++) {
            dateEnd += datechar[i];
        }
        for (int i = 0; i < 5; i++) {
            dateStart += datechar[i];
        }
        try {
            bool = dateFormat.parse(now).after(dateFormat.parse(dateStart)) &&
                    dateFormat.parse(now).before(dateFormat.parse(dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (bool){
            res = "Работает";
        }


        return res;
    }
    public static int isDateColor(String date){
        int res = Color.RED;
        Date currentDate = new Date();
        boolean bool = false;
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String now = dateFormat.format(currentDate);
        char[] datechar = date.toCharArray();
        String dateStart = "";
        String dateEnd = "";
        for (int i = 8; i < date.length(); i++) {
            dateEnd += datechar[i];
        }
        for (int i = 0; i < 5; i++) {
            dateStart += datechar[i];
        }
        try {
            bool = dateFormat.parse(now).after(dateFormat.parse(dateStart)) &&
                    dateFormat.parse(now).before(dateFormat.parse(dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (bool){
            res = Color.GREEN;
        }

        return res;
    }
}