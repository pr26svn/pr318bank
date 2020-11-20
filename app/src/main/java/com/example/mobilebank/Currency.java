package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Currency extends AppCompatActivity {
    public static final String money_format = "#0.00";
    LinearLayout btn_back;
    TextView dateView;
    private static final String TAG = "myLogs";
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<Valute> currensies = new ArrayList<Valute>();
    CurrencyAdapter currencyAdapter;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        dateView = (TextView)findViewById(R.id.tvToday);
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        dateView.setText(dateText);
        btn_back = (LinearLayout)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        currencyAdapter = new CurrencyAdapter(this, currensies);
        ListView Currency_lv = (ListView) findViewById(R.id.Currency_lv);
        StringBuilder sb = new StringBuilder();
        tv = (TextView)findViewById(R.id.tvTest);
        //запуск асинхронного метода
        AsyncTask.execute(new Runnable() {
            @Override
            //подключение и получение данных с сайта
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date(System.currentTimeMillis()));
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
                HttpsURLConnection connection = null;
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    connection.connect();
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        String line;
                        while ((line = in.readLine()) != null){
                            sb.append(line);
                            sb.append("\n");
                        }
                        Log.d(TAG, "SB" + sb.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    InputSource is = new InputSource(new StringReader(sb.toString()));
                                    is.setEncoding("Cp1251");
                                    Parsing(is);
                                    Currency_lv.setAdapter(currencyAdapter);
                                } catch (ParserConfigurationException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (SAXException e) {
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
    }
    //осуществляется непосредственно сам парсинг
    private void Parsing(InputSource file_for_parsing) throws ParserConfigurationException, IOException, SAXException {
 //старт парсинга
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document  = builder.parse(file_for_parsing);

        Element wrapper = (Element) document.getElementsByTagName("ValCurs").item(0);
        //String wrapper_id = wrapper.getAttribute("ID");
        NodeList nodlistValute = document.getElementsByTagName("Valute");
        for (int i = 0; i < nodlistValute.getLength(); i++){
            if (nodlistValute.item(i).getNodeType() == Node.ELEMENT_NODE){
                //Log.d(TAG, "Прошел проверку");
                Element curr = (Element) nodlistValute.item(i);
                Valute valuta = new Valute(curr.getAttribute("ID"), "", "", "", "");
                NodeList childNotes = curr.getChildNodes();
                for (int j = 0; j < childNotes.getLength(); j++){
                    if (childNotes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNotes.item(j);
                        switch (childElement.getNodeName()){
                            case "CharCode":
                                valuta.SetName(childElement.getTextContent());
                                break;
                            case "Name":
                                valuta.SetFullName(childElement.getTextContent());
                                break;
                            case "Value":
                                //
                                valuta.SetValue(childElement.getTextContent());
                                break;
                            case "Nominal":
                                valuta.SetSell(childElement.getTextContent());
                        }
                        //Прошелся по свичу
                    }
                }
                currensies.add(valuta);
                //Добавил элемент в список
            }
        }
        //конец парсинга
    }
}

