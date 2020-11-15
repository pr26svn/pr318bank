package com.example.mobileBank;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CurrencyActivity extends AppCompatActivity {

    private ListView listViewCurrency;

    // список валют
    private ArrayList<Currency> currencies;

    // адаптер для заполнения ListView списком валют currencies
    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        TextView textViewDate = findViewById(R.id.txtDateCurrency);
        listViewCurrency = findViewById(R.id.listViewExchangeRates);

        currencies = new ArrayList<>();

        // добавление текущей даты на активити
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        final String date = dateFormat.format(new Date());
        textViewDate.setText(date);

        // StringBuilder для хранения строк из XML
        final StringBuilder sb = new StringBuilder();

        // выполнение запроса и парсинг в фоновом потоке
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // объявление соединения
                HttpsURLConnection connection = null;
                try {
                    // получение котировок за сегодняшний день
                    URL url = new URL("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date);

                    // создание соединения с get запросом
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.connect();  // запуск соединения

                    // проверка результата соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        // BufferedReader для чтения xml файла с кодировкой ch1251
                        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                        String str;
                        // проверка строки из xml И добавление ее в StringBuilder
                        while ((str = br.readLine()) != null) {
                            sb.append(str).append("\n");
                        }

                        br.close();

                        // источник данных
                        InputSource is = new InputSource(new StringReader(sb.toString()));
                        // парсинг
                        parser(is);

                        // передача данных в ListView в основном потоке
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // заполнение адаптера и вывод данных в ListView
                                currencyAdapter = new CurrencyAdapter(CurrencyActivity.this, R.layout.list_item_currency, currencies);
                                listViewCurrency.setAdapter(currencyAdapter);
                            }
                        });
                    }
                } catch (Throwable cause) {
                    cause.printStackTrace();
                } finally {
                    // отключение соединения
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });
    }

    /**
     * метод для парсинга xml файла
     */
    private void parser(InputSource file) throws ParserConfigurationException, IOException, SAXException {
        // создание построителя документа
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // получение дерева DOM документа из xml файла
        Document document = db.parse(file);

        // список валют из файла
        NodeList nodeList = document.getElementsByTagName("Valute");

        // просмотр всех элементов списка валют
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);

            // если нода не текст, то это валюта, работа продолжается
            if (element.getNodeType() != Node.TEXT_NODE) {

                // переменные для записи значений по каждой валюте
                String charCode = getValueByTag("CharCode", element);
                String name = getValueByTag("Name", element);
                String value = getValueByTag("Value", element);
                double buy = Double.parseDouble(value.replace(',', '.'));
                double sell = Double.parseDouble(value.replace(',', '.'));
                int flag = getResources().getIdentifier(
                        charCode.toLowerCase() + "_flag",
                        "mipmap",
                        getApplicationContext().getOpPackageName());

                // создание объекта с полученными данными и добавление в список валют
                Currency currency = new Currency(flag, charCode, name, sell, buy);
                currencies.add(currency);
            }
        }
    }

    /**
     * метод для получения элемента по тэгу
     */
    private String getValueByTag(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    /**
     * метод нажатия на кнопку "Главное меню", выход на стартовый экран
     */
    public void returnBack_Click(View view) {
        finish();
    }
}