package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

public class MainActivity extends AppCompatActivity {

    Button changeBankButton; // <- кнопка перехода в отделения и банкоматы
    Button valueCourseButton; // <- кнопка перехода на курсы и валюты
    Button signInButton; // <- кнопка входа

    TextView dateText; // <- текст для отображения даты
    TextView usdText; // <- текст для отображения usd
    TextView eurText; // <- текст для отображения eur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // инициализация кнопок
        changeBankButton = (Button)findViewById(R.id.button_1);
        // переход на отделения и банкоматы
        changeBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, changeBank.class);
                startActivity(intent);
            }
        });
        valueCourseButton = (Button)findViewById(R.id.button_2);
        // переход на курсы и валюты
        valueCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, valueCourse.class);
                startActivity(intent);
            }
        });
        signInButton = (Button)findViewById(R.id.button_3);
        // переход на диалоговое окно входа
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = findViewById(R.id.editText_1);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); // <- создание диалогового окна
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.activity_login, null));
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.loginButtonString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText == null){
                            Toast.makeText(getApplicationContext(),"Поле для ввода пустое",Toast.LENGTH_SHORT);
                        }
                    }
                });
                builder.setNegativeButton(R.string.cancelButtonString, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });

        // инициализация даты
        dateText = (TextView) findViewById(R.id.text_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateText.setText(dateFormat.format(new Date()));

        // инициализация текста
        usdText = (TextView)findViewById(R.id.text_usd);
        eurText = (TextView)findViewById(R.id.text_eur);

        //выгрузка данных для отображения курса usd и eur
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

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {

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
    private void parsingXMLFiles(InputSource filePath) throws SAXException, IOException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filePath);

        NodeList valuteNodeList = document.getElementsByTagName("Valute");

        for (int i = 0; i < valuteNodeList.getLength(); i++) {
            if (valuteNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element valute = (Element)valuteNodeList.item(i);
                NodeList childNodes = valute.getChildNodes();

                String charCode = "";
                String name = "";
                String value = "";
                ArrayList<Valutes> valuteList = new ArrayList<>();
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

                        Valutes val = new Valutes(charCode, name, value);
                        valuteList.add(val);
                    }
                }

                // установ
                for (int j = 0; j < valuteList.size(); j++) {
                    if (valuteList.get(j).getValueCode() == "USD") {
                        usdText.setText(valuteList.get(j).getValue());
                    }
                    else if (valuteList.get(j).getValueCode() == "EUR") {
                        eurText.setText(valuteList.get(j).getValue());
                    }
                }
            }
        }
    }
}