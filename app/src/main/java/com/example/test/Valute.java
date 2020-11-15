package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Valute extends AppCompatActivity {
    //необходимые переменные

    adapterValute adapter;
    ListView listView;
    ArrayList<Valutes> valuteList = new ArrayList<>();
    final String TAG="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valute);
        getSupportActionBar().hide();

        //переход с окна "Валюты" на мэйн окно
        Button button4 = (Button) findViewById(R.id.button8);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Valute.this, MainActivity.class);
                startActivity(intent);
            }
        });
        listView=(ListView) findViewById(R.id.listView);
        adapter=new adapterValute(this, valuteList);

        //работа в фоновом режиме с загрузкой данных
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //нахождение даты на данном пк для поиска информации за сегодняшний день
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date(System.currentTimeMillis()));
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;

                //работа с соединением
                HttpsURLConnection connection = null;
                final StringBuilder sb = new StringBuilder();
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    Log.d(TAG, "-1");

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    //проверка на супешность соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        Log.d(TAG, "-1");

                        //объявление reader'a, который будет читать мой файл и кодировки cp1251
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        String line;
                        //проверка строчки на пустоту. Если она не пустая, переносим её в наш билдер
                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                            sb.append('\n');
                        }
                    }
                    //метод для добавления моих данных в ListView
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //указываю мой билдер (источник данных для вывода)
                                InputSource is = new InputSource(new StringReader(sb.toString()));
                                is.setEncoding("Cp1251");

                                //парсинг и добавление данных в мой адаптер
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

    //метод парсинга
    private void parsingXMLFiles(InputSource file) throws SAXException, IOException, ParserConfigurationException {

        Log.d(TAG, "0");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        //поиск значения Valute для дальнейшей работы с ним
        NodeList valuteNodeList = document.getElementsByTagName("Valute");

        //цикл для поиска необходимых данных с файлы
        for (int i = 0; i < valuteNodeList.getLength(); i++) {
            Log.d(TAG, "1");
            if (valuteNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element valute = (Element)valuteNodeList.item(i);
                NodeList childNodes = valute.getChildNodes();

                String charCode = "";
                String name = "";
                String value = "";
                //прохождение по всем элементам списка и нахожу нужные, а так же их дальнейший вывод
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);
                        switch (childElement.getNodeName()) {
                            case "CharCode": {
                                charCode = childElement.getTextContent();
                            }break;
                            case "Name": {
                                name = childElement.getTextContent();
                            }break;
                            case "Value": {
                                value = childElement.getTextContent();
                            }break;
                        }
                    }
                }
                //помещение данных в адаптер
                Log.d(TAG, "2");
                Valutes val = new Valutes(charCode, name, value);
                valuteList.add(val);
            }
        }
    }


}