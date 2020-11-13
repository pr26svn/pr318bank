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
import org.json.JSONArray;
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
                HttpURLConnection connection = null;
                try {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String time = df.format(currentDate);
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder;
                    URL cbr = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + time);
                    connection = (HttpURLConnection) cbr.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Test", "test.v1");
                    InputStream stream = connection.getInputStream();

                    builder = factory.newDocumentBuilder();
                    Document document = builder.parse(stream);

                    NodeList nodeList = document.getElementsByTagName("Valute");

                    List<CurrencyClass> langList = new ArrayList<CurrencyClass>();
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        arrayList.add(getCurrency(nodeList.item(i)));
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
                } finally {
                    connection.disconnect();
                }

            }

        });


    }

    private static CurrencyClass getCurrency(Node node) {
        CurrencyClass cc = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            int flag;
            switch (getTagValue("CharCode", element)){
                case "AUD":
                    flag = R.mipmap.aud_flag;
                    break;
                case "AZN":
                    flag = R.mipmap.azn_flag;
                    break;
                case "GBP":
                    flag = R.mipmap.gbp_flag;
                    break;
                case "AMD":
                    flag = R.mipmap.amd_flag;
                    break;
                case "BYN":
                    flag = R.mipmap.byn_flag;
                    break;
                case "BGN":
                    flag = R.mipmap.bgn_flag;
                    break;
                case "BRL":
                    flag = R.mipmap.brl_flag;
                    break;
                case "HUD":
                    flag = R.mipmap.huf_flag;
                    break;
                case "HKD":
                    flag = R.mipmap.hkd_flag;
                    break;
                case "DKK":
                    flag = R.mipmap.dkk_flag;
                    break;
                case "USD":
                    flag = R.mipmap.usd_flag;
                    break;
                case "EUR":
                    flag = R.mipmap.eur_flag;
                    break;
                case "INR":
                    flag = R.mipmap.inr_flag;
                    break;
                case "KZT":
                    flag = R.mipmap.kzt_flag;
                    break;
                case "CAD":
                    flag = R.mipmap.cad_flag;
                    break;
                case "KGS":
                    flag = R.mipmap.kgs_flag;
                    break;
                case "CNY":
                    flag = R.mipmap.cny_flag;
                    break;
                case "MDL":
                    flag = R.mipmap.mdl_flag;
                    break;
                case "NOK":
                    flag = R.mipmap.nok_flag;
                    break;
                case "PLN":
                    flag = R.mipmap.pln_flag;
                    break;
                case "RON":
                    flag = R.mipmap.ron_flag;
                    break;
                case "XDR":
                    flag = R.mipmap.xdr_flag;
                    break;
                case "SGD":
                    flag = R.mipmap.sgd_flag;
                    break;
                case "TJS":
                    flag = R.mipmap.tjs_flag;
                    break;
                case "TRY":
                    flag = R.mipmap.try_flag;
                    break;
                case "TMT":
                    flag = R.mipmap.tmt_flag;
                    break;
                case "UZS":
                    flag = R.mipmap.uzs_flag;
                    break;
                case "UAH":
                    flag = R.mipmap.uah_flag;
                    break;
                case "CZK":
                    flag = R.mipmap.czk_flag;
                    break;
                case "SEK":
                    flag = R.mipmap.sek_flag;
                    break;
                case "CHF":
                    flag = R.mipmap.chf_flag;
                    break;
                case "ZAR":
                    flag = R.mipmap.zar_flag;
                    break;
                case "KRW":
                    flag = R.mipmap.krw_flag;
                    break;
                case "JPY":
                    flag = R.mipmap.jpy_flag;
                    break;
                default:
                    flag = R.mipmap.xdr_flag;

            }
            cc = new CurrencyClass(flag,
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