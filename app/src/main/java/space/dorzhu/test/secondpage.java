package space.dorzhu.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class secondpage extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    //TextView txt;
    Bankomat currentBankomat;
    private ArrayList<Bankomat> bankomats;
    AdapterBank adapterBank;
    ListView listView;


    public secondpage(){
        bankomats = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);
        listView=(ListView)findViewById(R.id.ListView2);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout1);
        linearLayout.setOnClickListener(this);
        go();
    }
        //вывод
    private void vivod(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterBank = new AdapterBank(secondpage.this, bankomats);
                listView.setAdapter(adapterBank);
            }
        });
    }

    //переход на главную страницу
    @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.linearLayout1:
                finish();
        }
    }

        //Здесь происходит парсинг JSon файла
    private void go(){
        AsyncTask.execute(new Runnable() {
            @Override public void run() {
                try{

                    //Создание HTTP-соединения
                    URL url = new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=Черкассы");
                    HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");


                    //Создание объекта
                    Object object = new JSONParser().parse(responseBodyReader);
                    JSONObject jsonObject = (JSONObject) object;
                    JSONArray jsonArray = (JSONArray) jsonObject.get("devices");

                    Iterator deviceItr = jsonArray.iterator();
                    Log.e("dssd","devises:");
                    while(deviceItr.hasNext()){
                        JSONObject test = (JSONObject) deviceItr.next();
                        JSONObject tw = (JSONObject) test.get("tw");
                        currentBankomat = new Bankomat();
                        String s = test.get("fullAddressRu").toString();
                        s = s.substring(s.indexOf("город")).replace("город", "г.");
                        currentBankomat.setAddress(s);
                        currentBankomat.setMon(tw.get("fri").toString());
                        currentBankomat.setPlaceRu(test.get("placeRu").toString());
                        try {
                            bankomats.add(currentBankomat);
                        }catch (Exception e){
                            Log.d("erorr",e.getMessage());
                        }
                    }
                    vivod();
                } catch (Exception e){
                    Log.e("Error", e.getMessage());
                }
            }
        });
    }
        //переход на главную страницу
    public void vihod(View view) {
        finish();
    }
}
