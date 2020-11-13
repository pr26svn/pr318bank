package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class secondActivity extends AppCompatActivity {
    Date date;
    TextView mainDate;
    TextView txtXml;

    ListView listView;
    CurrencyAdapter currencyAdapter;
    ArrayList<Currency> currencyArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.textView13);
        mainDate.setText(dateText);

        listView=(ListView)findViewById(R.id.listView1);
        new newThread().execute();
        currencyAdapter = new CurrencyAdapter(this, R.layout.list_style, currencyArrayList);
/*        String blokXml="";
        txtXml = (TextView) findViewById(R.id.textView13);
        XmlPullParser xpp = getResources().getXml(R.xml.currency);
        currencyParser parser = new currencyParser();
        if (parser.parse(xpp))
        {
            for (Currency currencyParser: parser.getCurrencies()){
                blokXml = blokXml + currencyParser.toString() + "\n";
            }
            txtXml.setText(blokXml);
        }*/
    }

    public class newThread extends AsyncTask<String,  Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Document doc;
            int flg = 0;
            try{
                doc = Jsoup.connect("https://www.profinance.ru/currency_usd.asp%22").get();
                        Elements elements = doc.select("tr.stat");
                currencyArrayList.clear();


                for(Element element : elements){
                    if(flg!= 0){
                        String title = element.child(0).text();
                        String name = element.child(2).text();
                        String value = element.child(3).text();


                        currencyArrayList.add(new Currency(name,
                                title, value, R.drawable.usa));
                    } else{
                        flg = 1;
                    }

                }



            }catch ( IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            listView.setAdapter(currencyAdapter);
        }
    }

    public void finish(View view) {
        finish();
    }
}
