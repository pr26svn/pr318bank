package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

// Класс главного окна приложения
public class MainActivity extends AppCompatActivity{

    TextView dateView;
    TextView tvUSDValue;
    TextView tvEURValue;
    String eur = null;
    String usd = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEURValue = (TextView) findViewById(R.id.tvEURValue);
        tvUSDValue = (TextView) findViewById(R.id.tvUSDValue);
        dateView = (TextView) findViewById(R.id.tvDate);
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        dateView.setText(dateText);

        // Создание потока, для получения данных с сайта
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    // Форматирование времени как "день/месяц/год"
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String time = df.format(currentDate);

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder;

                    // Подключение к сайту
                    URL cbr = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + time);
                    connection = (HttpURLConnection) cbr.openConnection();
                    connection.setRequestMethod("GET");

                    InputStream stream = connection.getInputStream();
                    // Начало парсинга сайта
                    builder = factory.newDocumentBuilder();
                    Document document = builder.parse(stream);

                    // Получение элементов по тегу "Valute"
                    NodeList nodeList = document.getElementsByTagName("Valute");

                    // Получение курса доллара и евро
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        if (getCurrency(nodeList.item(i), "EUR") != null)
                            eur = getCurrency(nodeList.item(i), "EUR");
                        if (getCurrency(nodeList.item(i), "USD") != null)
                         usd = getCurrency(nodeList.item(i), "USD");
                    }

                    // Создание еще одного потока для вывода на экран
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        tvEURValue.setText(eur);
                        tvUSDValue.setText(usd);
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
    // Метод для перехода на окна валют
    public void onClickCurrency(View view) {
        Intent intent = new Intent(this, Currency.class);
        startActivity(intent);
    }

    // Метод для перехода на окна банкоматов и отделений
    public void onClickAtms(View view) {
        Intent intent = new Intent(this, Atms.class);
        startActivity(intent);
    }

    // Метод для открытия диалогового окна со входом
    public void openDialog(View v) {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    // Получение валюты и ее форматирование
    private static String getCurrency(Node node, String tag) {
        String res = null;
        if ((node.getNodeType() == Node.ELEMENT_NODE)) {
            Element element = (Element) node;
            if (getTagValue("CharCode", element).equals(tag)) {
                res = String.format("%.1f", Double.parseDouble(getTagValue("Value",
                        element).replace(',', '.'))).replace('.', ',');
            }

        }

        return res;
    }

    // Получаем значения элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}