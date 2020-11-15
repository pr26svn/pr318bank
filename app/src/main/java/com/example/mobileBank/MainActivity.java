package com.example.mobileBank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    TextView textViewDate;
    TextView textViewUsdRate;
    TextView textViewEurRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = findViewById(R.id.txtDate);
        textViewUsdRate = findViewById(R.id.txtUsdRate);
        textViewEurRate = findViewById(R.id.txtEurRate);

        //добавление даты на стартовый экран
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        final String date = dateFormat.format(new Date());
        textViewDate.setText(date);

        // вывод на стартовый экран актуального курса доллара и евро

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

    //метод нажатия на кнопку "Отделения и банкоматы"
    public void branchesAndATMs_Click(View view) {
        Intent intent = new Intent(MainActivity.this, BankActivity.class);
        startActivity(intent);
    }

    //метод нажатия на кнопку "Курсы валют"
    public void exchangeRates_Click(View view) {
        Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
        startActivity(intent);
    }

    //метод нажатия на кнопку "Войти" и вызова диалогового окна авторизации
    public void signin_Click(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.DialogTheme));
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        builder.setCancelable(false);

        //кнопка Войти
        builder.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
            }
        });

        //кнопка Отмена
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    /**
     * метод для парсинга xml файла и изменения курса валют на стартовом экране
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
                double value = Double.parseDouble(getValueByTag("Value", element).replace(',', '.'));
                String rate = new DecimalFormat("#0.00").format(value);
                if (charCode.contains("USD"))
                    textViewUsdRate.setText(rate);
                else if (charCode.contains("EUR"))
                    textViewEurRate.setText(rate);
            }
        }
    }

    /**
     * метод для получения элемента по тэгу
     */
    private String getValueByTag(String tag, Element element) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}