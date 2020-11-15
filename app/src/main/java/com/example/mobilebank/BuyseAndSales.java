package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

import static com.example.mobilebank.R.*;

public class BuyseAndSales extends AppCompatActivity {
    Date date;
    TextView mainDate;
    SalesAdapter adapter;
    ListView listView;
    ArrayList<Sales> Saleslist = new ArrayList<>();
    final String TAG="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_buyse_and_sales);

        /* Добавление даты в textview на окне "Курсы валют" */
        date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dataText=dateFormat.format(date);
        mainDate=(TextView) findViewById(id.dateTextView);
        mainDate.setText(dataText);



        /* Добавление listview */
        listView = findViewById(id.listViewBuyse);
        adapter = new SalesAdapter(this, Saleslist);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date(System.currentTimeMillis()));
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;

                HttpsURLConnection connection = null;
                final StringBuilder sb = new StringBuilder();
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    Log.d(TAG, "-1");

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        Log.d(TAG, "-1");

                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        String line;
                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                            sb.append('\n');
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                InputSource is = new InputSource(new StringReader(sb.toString()));
                                is.setEncoding("Cp1251");

                                parsingXMLFiles(is);
                                listView.setAdapter(adapter);

                            } catch (SAXException e) {
                                e.printStackTrace();
                            } catch (ParserConfigurationException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void parsingXMLFiles(InputSource file) throws SAXException, IOException, ParserConfigurationException {

        Log.d(TAG, "0");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList valuteNodeList = document.getElementsByTagName("Sales");

        for (int i = 0; i < valuteNodeList.getLength(); i++) {
            Log.d(TAG, "1");
            if (valuteNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element valute = (Element)valuteNodeList.item(i);
                NodeList childNodes = valute.getChildNodes();

                String usd = "";
                String usd_dollar = "";
                String course_up = "";
                String course_down = "";
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);
                        switch (childElement.getNodeName()) {
                            case "usd": {
                                usd = childElement.getTextContent();
                            }break;
                            case "usd_dollar": {
                                usd_dollar = childElement.getTextContent();
                            }break;
                            case "course_up": {
                                course_up = childElement.getTextContent();
                            }break;
                            case "course_down": {
                                course_down = childElement.getTextContent();
                            }break;
                        }
                    }
                }
                Log.d(TAG, "2");
                Sales val = new Sales(usd, usd_dollar, course_up, course_down);
                Saleslist.add(val);
            }
        }


        /*arrayList.add(new Sales(drawable.flag, "USD", "Американская валюта", "68", "70"));

        SalesAdapter salesAdapter = new SalesAdapter(this, layout.list_row_sales, arrayList);

        listView.setAdapter(salesAdapter);*/
    }


    /* Переход с окна "Курсы валют" на главную */
    public void MainPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}