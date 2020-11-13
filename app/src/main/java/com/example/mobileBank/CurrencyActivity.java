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
        currencyAdapter = new CurrencyAdapter(this, R.layout.list_item_currency, currencies);

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
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Test", "test");
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

                        // передача данных в ListView в основном потоке
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    // источник данных
                                    InputSource is = new InputSource(new StringReader(sb.toString()));

                                    // парсинг
                                    parser(is);

                                    // вывод данных в ListView
                                    listViewCurrency.setAdapter(currencyAdapter);
                                } catch (ParserConfigurationException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (SAXException e) {
                                    e.printStackTrace();
                                }
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
            Node node = nodeList.item(i);

            // если нода не текст, то это валюта, работа продолжается
            if (node.getNodeType() != Node.TEXT_NODE) {

                // список значений валюты
                NodeList childNodes = node.getChildNodes();

                // переменные для записи значений по каждой валюте
                int flag = 0;
                String charCode = null;
                String name = null;
                double buy = 0.0;
                double sell = 0.0;

                // просмотр всех значений конкретной валюты
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() != Node.TEXT_NODE) {
                        Element childElement = (Element) childNodes.item(j);

                        // если тэг хранит необходимые данные, они присваиваются объявленным выше переменным
                        switch (childElement.getNodeName()) {
                            case "CharCode":
                                flag = getResources().getIdentifier(
                                        childElement.getTextContent().toLowerCase() + "_flag",
                                        "mipmap",
                                        getApplicationContext().getOpPackageName());

                                charCode = childElement.getTextContent();
                                break;
                            case "Name":
                                name = childElement.getTextContent();
                                break;
                            case "Value":
                                buy = Double.parseDouble(childElement.getTextContent().replace(',', '.'));
                                sell = Double.parseDouble(childElement.getTextContent().replace(',', '.'));
                                break;
                        }
                    }
                }

                // создание объекта с полученными данными и добавление в список валют
                Currency currency = new Currency(flag, charCode, name, sell, buy);
                currencies.add(currency);
            }
        }
    }

    /**
     * метод нажатия на кнопку "Главное меню", выход на стартовый экран
     */
    public void returnBack_Click(View view) {
        finish();
    }
}