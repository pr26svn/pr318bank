package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageButton;
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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Currency extends AppCompatActivity {


    //Объявляю элементы интерфейса
    ImageButton btn_back;
    TextView curr_date;
    ListView Currency_lv;
    TextView tv;



    // это тэг для того, чтобы различать мои записи в Logcat
    private static final String TAG = "myLogs";


    //Это массив валют. В дальнейшем буду просто в него добавлять элементы
    // и скармливать его ListView
    ArrayList<Valuta> currensies = new ArrayList<Valuta>();

    //а поможет мне в этом мой Adapter для ListView
    CurrencyAdapter currencyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        //убираю верхнюю панель
        getSupportActionBar().hide();

        //Инициализирую элементы интерфейса и поля
        tv = (TextView)findViewById(R.id.tvTest);
        curr_date = (TextView)findViewById(R.id.curr_date);
        btn_back = (ImageButton)findViewById(R.id.btn_back2);
        Currency_lv = (ListView) findViewById(R.id.Currency_lv);
        currencyAdapter = new CurrencyAdapter(this, currensies);


        //получаю текущую дату
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date(System.currentTimeMillis()));

        // и вывожу
        curr_date.setText(date);

        // кнопка всего одна на активити
        // поэтому при нажатии сразу же уничтожаю текущее окно через finish()
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

                        //цикл
                        // проверяем значение из XML
                        // и довабляем в наш builder с переносом для красоты
                        while ((line = in.readLine()) != null){
                            sb.append(line);
                            sb.append("\n");
                        }


                        // просто так передать данные ListView не получится
                        // это нельзя делать в основном потоке
                        // поэтому использую метод ниже
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    //объявляю источник данных (наш builder)
                                    InputSource is = new InputSource(new StringReader(sb.toString()));

                                    //мой метод для парсинга
                                    Parsing(is);

                                    //и вот теперь, когда все запарсили
                                    //данные получены, мы можем вывести их в ListView
                                    Currency_lv.setAdapter(currencyAdapter);



                                } catch (ParserConfigurationException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (SAXException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else{
                        Log.d(TAG, "error");
                    }

                } catch (Throwable cause){
                    cause.printStackTrace();
                }finally {
                    //В конце обязятельно отключаемся
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });






    }

    //мой метод для парсинга
    private void Parsing(InputSource file_for_parsing) throws ParserConfigurationException, IOException, SAXException {
        Log.d(TAG, "Starting parsing");


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document  = builder.parse(file_for_parsing);

        // это будет списко валют
        NodeList nodeListValute = document.getElementsByTagName("Valute");


        //прохожу по всем элементам списка
        for (int i = 0; i < nodeListValute.getLength(); i++){
            if (nodeListValute.item(i).getNodeType() == Node.ELEMENT_NODE){

                Element curr = (Element) nodeListValute.item(i);


                NodeList childNotes = curr.getChildNodes();

                String CharCode = "", Name = "";
                int NumCode = 0;
                double Value = 0;
                int Nominal = 0;


                for (int j = 0; j < childNotes.getLength(); j++){
                    if (childNotes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNotes.item(j);

                        switch (childElement.getNodeName()){
                            case "NumCode":
                                NumCode = Integer.parseInt(childElement.getTextContent());
                            case "CharCode":
                                CharCode = childElement.getTextContent();
                                break;
                            case "Name":
                                Name = childElement.getTextContent();
                                break;
                            case "Value":
                                Value = Double.parseDouble(childElement.getTextContent().replace("," , "."));
                                break;
                            case "Nominal":
                                Nominal = Integer.parseInt(childElement.getTextContent());
                                break;
                        }

                    }
                }
                //создаю объект с изъятыми данными
                Valuta valuta = new Valuta(NumCode, CharCode, Name, Value, Nominal);

                // и добавляю его в мой массив
                //этот массив потом скормлю адаптеру
                currensies.add(valuta);
            }

        }
    }

}

