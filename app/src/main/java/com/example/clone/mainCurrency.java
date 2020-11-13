package com.example.clone;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class mainCurrency extends AppCompatActivity {

    public String dollar;
    public String euro;
    ListView listView;
    PersonAdapter currencyAdapter;
    ArrayList<Currency> currencyArrayList= new ArrayList<>();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_currency);

        Date date;

        TextView mainDate;

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.textViewOfDate);
        mainDate.setText(dateText);

        listView = (ListView) findViewById(R.id.listCurrency);



        new newThread().execute();
        currencyAdapter = new PersonAdapter(this, R.layout.list_main, currencyArrayList);

    }

    public class newThread extends AsyncTask<String,  Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            Document doc;
            int flag = 0;
            try{
                doc = Jsoup.connect("https://www.profinance.ru/currency_usd.asp").get();
                Elements elements = doc.select("tr.stat");
                currencyArrayList.clear();
                String value = "";


                for(Element element : elements){
                    if(flag > 0){
                        String title = element.child(0).text();
                        String name = element.child(2).text();
                        value = element.child(3).text();

                        if(flag == 5){
                            dollar = value;
                        }else if(flag == 6){
                            euro = value;
                        }
                        switch (flag){
                            case 1:
                                currencyArrayList.add(new Currency(R.drawable.gbp_main, name,
                                        title, value));
                            case 2:
                                currencyArrayList.add(new Currency(R.drawable.usa, name,
                                        title, value));
                            default:
                                currencyArrayList.add(new Currency(R.drawable.gbp_main, name,
                                        title, value));

                        }



                    }
                    flag++;
                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            listView.setAdapter(currencyAdapter);
        }
    }

    public void onClickBack(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}