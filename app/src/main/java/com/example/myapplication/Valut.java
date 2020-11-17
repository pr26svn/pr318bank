package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
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

public class Valut extends AppCompatActivity {
    //переменные
    ValutAdapter adapter;
    ListView listView;
    ArrayList<ValutResourse> valuteList = new ArrayList<>();
    final String tag="";
    @Override
    //Представляет собой первоначальную настройку activity,в частности, создаются объекты визуального интерфейса
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valut);
        getSupportActionBar().hide();
        listView=(ListView) findViewById(R.id.listView);
        adapter=new ValutAdapter(this, valuteList);
        AsyncTask.execute(new Runnable() {
            @Override
            //Указываем ссылку на сайт,работаем с данными из АPI файла, происходит фоновая загрузка
            public void run() {
                //поиск информации за сегодняшний день
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date(System.currentTimeMillis()));
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
                //соединение
                HttpsURLConnection connection = null;
                final StringBuilder sb = new StringBuilder();
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    Log.d(tag, "-1");
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    connection.connect();
                    //проверка успешности соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        Log.d(tag, "-1");
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        String line;
                        while ((line = in.readLine()) != null) {
                            sb.append(line);
                            sb.append('\n');
                        }
                    }
                    //обновление данных в списке ListView
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                InputSource is = new InputSource(new StringReader(sb.toString()));
                                is.setEncoding("Cp1251");
                                //парсинг и добавление данных в адаптер
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
//Парсинг информации
    private void parsingXMLFiles(InputSource file) throws SAXException, IOException, ParserConfigurationException {

        Log.d(tag, "0");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        NodeList valuteNodeList = document.getElementsByTagName("Valute");
        for (int i = 0; i < valuteNodeList.getLength(); i++) {
            Log.d(tag, "1");
            if (valuteNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element valute = (Element)valuteNodeList.item(i);
                NodeList childNodes = valute.getChildNodes();

                String charCode = "";
                String name = "";
                String value = "";
                //поиск нужной информации в списке и дальнейший ее вывод
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
                Log.d(tag, "2");
                ValutResourse val = new ValutResourse(charCode, name, value);
                valuteList.add(val);
            }
        }
    }
//Переход на главное меню из списка валют
    public void onMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
