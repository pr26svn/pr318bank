package com.example.myapplication;

import android.app.AppComponentFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class firstActivity extends AppCompatActivity {

    Atm_adapter atm_adapter;
    ArrayList<Atm>atms = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstacrivity);
        listView=(ListView) findViewById(R.id.list_atm);

        /*Вызов фонового парсинга*/
        new firstActivity.newThreadTwo().execute();
        atm_adapter = new Atm_adapter(this, R.layout.atm_style, atms);
    }

    public class newThreadTwo extends AsyncTask<String,  Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Document doc;
            String title = "";
            String time = "";

            ArrayList<String> titles = new ArrayList<>();
            ArrayList<String> times = new ArrayList<>();
            try{

                doc = Jsoup.connect("https://tnkfb.ru/adresa/gorod/omsk/").get();
                                Elements elementsTitle = doc.select("div.bank_title");
                Elements elementsTime = doc.select("div.vr");

                atms.clear();
                String value = "";

                for(Element element : elementsTitle){
                    title = element.child(0).text();
                    titles.add(title);
                }
                for(Element element : elementsTime){
                    time  = element.text();
                    times.add(time);
                }
                for(int i = 0; i < titles.size(); i++)
                    atms.add(new Atm(titles.get(i), times.get(i), "Работает", "Отделение"));


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            listView.setAdapter(atm_adapter);
        }
    }

    public void finish(View view) {
        finish();
    }
}
