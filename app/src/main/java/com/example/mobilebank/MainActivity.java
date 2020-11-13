 package com.example.mobilebank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myTag";
    TextView tvDate,tvUSD, tvEUR;
    boolean var_USD = false, var_EUR = false;
    //String curr_USD, curr_EUR;

    private static final String USD = "USD";
    private static final String EUR = "EUR";
    public static final String cours_format = "#0.00";

    // переменная для уменьшения кода
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Получение актуальной даты
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvUSD = (TextView) findViewById(R.id.tvUSD);
        tvEUR = (TextView) findViewById(R.id.tvEUR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        tvDate.setText(date);
        //этот билдер нужен для того, что бы добавлять в него строки из XML
        StringBuilder sb = new StringBuilder();
        // код, выполняемый в фоне
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // получаю котировки именно на сегодняшний день
                // query - URL в котором лежит XML с данными
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
                //объявляю HTTPS соединение
                HttpsURLConnection connection = null;
                try {
                    //открываю соединение
                    connection = (HttpsURLConnection) new URL(query).openConnection();
                    // для ясности добавил, что нужно использовать GET
                    // но он и так стоит по умолчанию
                    connection.setRequestMethod("GET");
                    //установил заголовок, чтобы сервер понимал, кто к нему обращается
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    //начинаю соединение
                    connection.connect();
                    // проверка на успешность соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){
                        //объявляют reader, который будет читать мой XMl и передаю кодировку ch1251(иначе будут знаки вопроса за место русских букв)
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        //вспомогательная переменная
                        String line;
                        while ((line = in.readLine()) != null){
                            sb.append(line);
                            sb.append("\n");
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //объявление источника данных
                                    InputSource is = new InputSource(new StringReader(sb.toString()));
                                    //вызов парсинга
                                    Parsing(is);

                                } catch (ParserConfigurationException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (SAXException e) {
                                    e.printStackTrace();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (Throwable cause){
                    cause.printStackTrace();
                }finally {
                    //отключение
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });
    }
    //Открытие окна отделений
    public void onClickOtd(View v){
        Intent intent = new Intent(this, a_t_ms.class);
        startActivity(intent);
    }
    //Открытие окна курса валют
    public void onClickCur(View v){
        Intent intent = new Intent(this, Currency.class);
        startActivity(intent);
    }
    //Запуск кастомного диалога
    public void onClickEnter(View view){
        myCustomDialog();
    }
    //Создаем кастомный диалог
    private void myCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ConstraintLayout cl = (ConstraintLayout) getLayoutInflater().inflate(R.layout.my_dialog, null);
        builder.setView(cl);
        builder.show();
    }
    //Парсинг
    private void Parsing(InputSource file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        //старт парсинга
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document  = builder.parse(file_for_parsing);
        // Список валют
        NodeList nodeListValute = document.getElementsByTagName("Valute");
        //проход по всем элементам сиска
        for (int i = 0; i < nodeListValute.getLength(); i++){
            if (nodeListValute.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element curr = (Element) nodeListValute.item(i);
                NodeList childNotes = curr.getChildNodes();
                String CharCode = "";
                double Value = 0;

                for (int j = 0; j < childNotes.getLength(); j++){
                    if (childNotes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNotes.item(j);
                        switch (childElement.getNodeName()){
                            case "CharCode":
                                CharCode = childElement.getTextContent();
                                break;
                            case "Value":
                                Value = Double.parseDouble(childElement.getTextContent().replace("," , "."));
                                break;
                        }
                    }
                }
                //нахождение доллара
                if (CharCode.equals(USD)){
                    Value = Value+(Value*1.5);
                    String formattedDouble = new DecimalFormat(cours_format).format(Value);
                    tvUSD.setText(formattedDouble);
                } else if (CharCode.equals(EUR)){
                    //нахождение евро
                    Value = Value+(Value*1.5);
                    String formattedDouble = new DecimalFormat(cours_format).format(Value);
                    tvEUR.setText(formattedDouble);

                }
            }
        }

    }
}