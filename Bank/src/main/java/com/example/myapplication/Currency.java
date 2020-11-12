package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Currency extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Currency";
    ListView listView;
    TextView dateView;
    ArrayList<CurrencyClass> arrayList = new ArrayList<>();
    CurrencyAdapter currencyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        dateView = (TextView) findViewById(R.id.tvDate);
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        dateView.setText(dateText);

        listView = findViewById(R.id.list);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder;
                    URL cbr = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=09/11/2020");
                    HttpURLConnection myConnection = (HttpURLConnection) cbr.openConnection();
                    myConnection.setRequestProperty("Test", "test.v1");
                    InputStream stream = myConnection.getInputStream();

                    builder = factory.newDocumentBuilder();
                    Document document = builder.parse(stream);

                    // получаем узлы с именем Language
                    // теперь XML полностью загружен в память
                    // в виде объекта Document
                    NodeList nodeList = document.getElementsByTagName("Valute");

                    // создадим из него список объектов Language
                    List<CurrencyClass> langList = new ArrayList<CurrencyClass>();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        langList.add(getCurrency(nodeList.item(i)));
                    }

                    // печатаем в консоль информацию по каждому объекту Language
                    for (CurrencyClass cc : langList) {
                        arrayList.add(new CurrencyClass(cc.getFlag(), cc.getCurrency(), cc.getName(), cc.getBuy(),
                                cc.getSell()));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currencyAdapter = new CurrencyAdapter(Currency.this, R.layout.list_row_currency, arrayList);
                            listView.setAdapter(currencyAdapter);
                        }
                    });

                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }

        });


    }

    private static CurrencyClass getCurrency(Node node) {
        CurrencyClass cc = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
//            int flag;
//            switch (getTagValue("CharCode", element)){
//                case "AUD":
//                    flag = R.drawable.aud_flag;
//                    break;
//                case "AZN":
//                    flag = R.drawable.azn_flag;
//                    break;
//                case "GBP":
//                    flag = R.drawable.gbp_flag;
//                    break;
//                case "AMD":
//                    flag = R.drawable.amd_flag;
//                    break;
//                case "BYN":
//                    flag = R.drawable.byn_flag;
//                    break;
//                case "BGN":
//                    flag = R.drawable.bgn_flag;
//                    break;
//                case "BRL":
//                    flag = R.drawable.brl_flag;
//                    break;
//                case "HUD":
//                    flag = R.drawable.huf_flag;
//                    break;
//                case "HKD":
//                    flag = R.drawable.hkd_flag;
//                    break;
//                case "DKK":
//                    flag = R.drawable.dkk_flag;
//                    break;
//                case "USD":
//                    flag = R.drawable.usd_flag;
//                    break;
//                case "EUR":
//                    flag = R.drawable.eur_flag;
//                    break;
//                case "INR":
//                    flag = R.drawable.inr_flag;
//                    break;
//                case "KZT":
//                    flag = R.drawable.kzt_flag;
//                    break;
//                case "CAD":
//                    flag = R.drawable.cad_flag;
//                    break;
//                case "KGS":
//                    flag = R.drawable.kgs_flag;
//                    break;
//                case "CNY":
//                    flag = R.drawable.cny_flag;
//                    break;
//                case "MDL":
//                    flag = R.drawable.mdl_flag;
//                    break;
//                case "NOK":
//                    flag = R.drawable.nok_flag;
//                    break;
//                case "PLN":
//                    flag = R.drawable.pln_flag;
//                    break;
//                case "RON":
//                    flag = R.drawable.ron_flag;
//                    break;
//                case "XDR":
//                    flag = R.drawable.xdr_flag;
//                    break;
//                case "SGD":
//                    flag = R.drawable.sgd_flag;
//                    break;
//                case "TJS":
//                    flag = R.drawable.tjs_flag;
//                    break;
//                case "TRY":
//                    flag = R.drawable.try_flag;
//                    break;
//                case "TMT":
//                    flag = R.drawable.tmt_flag;
//                    break;
//                case "UZS":
//                    flag = R.drawable.uzs_flag;
//                    break;
//                case "UAH":
//                    flag = R.drawable.uah_flag;
//                    break;
//                case "CZK":
//                    flag = R.drawable.czk_flag;
//                    break;
//                case "SEK":
//                    flag = R.drawable.sek_flag;
//                    break;
//                case "CHF":
//                    flag = R.drawable.chf_flag;
//                    break;
//                case "ZAR":
//                    flag = R.drawable.zar_flag;
//                    break;
//                case "KRW":
//                    flag = R.drawable.krw_flag;
//                    break;
//                case "JPY":
//                    flag = R.drawable.jpy_flag;
//                    break;
//                default:
//                    flag = 0;
//
//            }
            cc = new CurrencyClass(R.drawable.xdr_flag,
                    getTagValue("CharCode", element),
                    getTagValue("Name", element),
                    String.format("%.2f", Double.parseDouble(getTagValue("Value", element).replace(',', '.'))).replace('.', ','),
                    String.format("%.2f", Double.parseDouble(getTagValue("Value", element).replace(',', '.'))).replace('.', ','));

        }

        return cc;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}