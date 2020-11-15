package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.HttpResponseCache;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    //необходимые переменные
    TextView usdText;
    TextView eurText;
    private static final String USD = "USD";
    private static final String EUR = "EUR";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //спрятала шторку с названием проекта
        getSupportActionBar().hide();



        //переменная для вывода даты на кнопке "валюты"
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        //инициализация
        TextView Date_txt = findViewById(R.id.textView9);
        Date_txt.setText((format.format(new Date())));
        usdText = (TextView)findViewById(R.id.textView4);
        eurText = (TextView)findViewById(R.id.textView7);

        Button button2 = (Button) findViewById(R.id.button);
        //диалоговое окно на кнопке "войти"
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = findViewById(R.id.Et);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.builder);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.activity_log_in, null));
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.button5, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText == null){
                            Toast.makeText(getApplicationContext(),"Поле для ввода пустое",Toast.LENGTH_SHORT);
                        }
                    }
                });
                builder.setNegativeButton(R.string.button4, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
        //выволнение в фоне скачивания файла
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //обработка времени на компбютере для скачивания файла сегодняшней даты
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date(System.currentTimeMillis()));
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;


                //работа с соедниением
                HttpsURLConnection connection = null;
                final StringBuilder sb = new StringBuilder();
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    //если соединение успешно, то работаем с ним
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {


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

                                //парсинг
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
    //метод парсинга
    private void parsingXMLFiles(InputSource filePath) throws SAXException, IOException, ParserConfigurationException {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filePath);

        //поиск значения Valute для дальнейшей работы с ним
        NodeList valuteNodeList = document.getElementsByTagName("Valute");

        //цикл для поиска необходимых данных с файлы
        for (int i = 0; i < valuteNodeList.getLength(); i++) {
            if (valuteNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element valute = (Element) valuteNodeList.item(i);
                NodeList childNodes = valute.getChildNodes();

                String charCode = "";
                String name = "";
                String value = "";
                //создание листа данных
                ArrayList<Valutes> valuteList = new ArrayList<>();
                //прохождение по всем элементам списка и нахожу нужные, а так же их дальнейший вывод
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);
                        switch (childElement.getNodeName()) {
                            case "CharCode": {
                                charCode = childElement.getTextContent();
                            }
                            break;
                            case "Name": {
                                name = childElement.getTextContent();
                            }
                            break;
                            case "Value": {
                                value = childElement.getTextContent();
                            }
                            break;
                        }

                        Valutes val = new Valutes(charCode, name, value);
                        valuteList.add(val);
                    }
                }
                //вывод значений доллара и евро на кнопку "Валюты"

                if (charCode.equals(USD)) {
                    String formattedDouble = value;
                    usdText.setText(formattedDouble);
                } else if (charCode.equals(EUR)) {
                    String formattedDouble =value;
                    eurText.setText(formattedDouble);
                }
            }

        }
    }
    //переход с мэйна на окно с банкоматами
    public void ATM(View view) {
        Intent intent = new Intent(MainActivity.this, Bank.class);
        startActivity(intent);    }

    //переход с мэйна на окно с валютами
    public void valute(View view) {
        Intent intent = new Intent(MainActivity.this, Valute.class);
        startActivity(intent);
    }
}


