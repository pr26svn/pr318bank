package com.example.mobilebank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTag";
    //Объявляют элементы интерфейса
    LinearLayout btn_otdelenia, btn_currency;
    Button btn_enter;
    TextView tvDate, tvUSD, tvEUR;
    String date;



    boolean var_USD = false, var_EUR = false;
    //String curr_USD, curr_EUR;

    private static final String USD = "USD";
    private static final String EUR = "EUR";
    public static final String money_format = "#0.00";

    // переменная для уменьшения кода
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //убираю верхнюю панель
        getSupportActionBar().hide();

        //Инициализирую элементы интерфейса
        btn_otdelenia = (LinearLayout) findViewById(R.id.btn_otdelenia);
        btn_currency = (LinearLayout) findViewById(R.id.btn_currency);
        btn_enter = (Button) findViewById(R.id.btn_enter);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvUSD = (TextView) findViewById(R.id.tvUSD);
        tvEUR = (TextView) findViewById(R.id.tvEUR);


        //получаю текущую дату
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        date = sdf.format(new Date(System.currentTimeMillis()));

        //вывожу ее в TextView
        tvDate.setText(date);


        //присваювию кнопкам обработчики нажатия(Сама MainActivity)
        btn_otdelenia.setOnClickListener(this);
        btn_currency.setOnClickListener(this);
        btn_enter.setOnClickListener(this);


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

                                   // tvUSD.setText(curr_USD);
                                   // tvEUR.setText(curr_EUR);

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

    @Override
    public void onClick(View v) {
        // в зависимости от нажатой кнопки
        // будет вызываться та или иная Activity
        Intent intent;
        switch (v.getId()){
            case R.id.btn_otdelenia:
                 intent = new Intent(this, Otdelenia.class);
                 startActivity(intent);
                 break;
            case R.id.btn_currency:
                 Intent intent2 = new Intent(this, Currency.class);
                 startActivity(intent2);
                 break;
            case R.id.btn_enter:
                //вызываю метод, в котором я создаю свой AlertDialog
                createDialog();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }


    //метод, в котором я создаю свой AlertDialog
    void createDialog() {
        //Получаем вид с файла registration.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        View registrationView = li.inflate(R.layout.registration, null);


        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем registration.xml для нашего AlertDialog:
        mDialogBuilder.setView(registrationView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInputLogin = (EditText) registrationView.findViewById(R.id.input_login);
        final EditText userInputPassword = (EditText) registrationView.findViewById(R.id.input_password);


        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // в задании не было сказано, что должно происходить по нажатию "ок"
            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();
    }


    private void Parsing(InputSource file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        Log.d(TAG, "Starting parsing");


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document  = builder.parse(file_for_parsing);

        // это будет списко валют
        NodeList nodeListValute = document.getElementsByTagName("Valute");


        //прохожу по всем элементам списка
        for (int i = 0; i < nodeListValute.getLength(); i++){
            Log.d(TAG, "1");
            if (nodeListValute.item(i).getNodeType() == Node.ELEMENT_NODE){

                Element curr = (Element) nodeListValute.item(i);


                NodeList childNotes = curr.getChildNodes();



                String CharCode = "";
                double Value = 0;


                for (int j = 0; j < childNotes.getLength(); j++){
                    Log.d(TAG, "2");
                    if (childNotes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Log.d(TAG, "3");
                        Element childElement = (Element) childNotes.item(j);

                        switch (childElement.getNodeName()){
                            case "CharCode":
                                Log.d(TAG, "4");
                                CharCode = childElement.getTextContent();
                                break;
                            case "Value":
                                Log.d(TAG, "5");
                                Value = Double.parseDouble(childElement.getTextContent().replace("," , "."));
                                break;
                        }

                    }
                }



                Log.d(TAG, "CharCode is " + CharCode);
                if (CharCode.equals(USD)){
                    Log.d(TAG, "I find USD!!!");
                    String formattedDouble = new DecimalFormat(money_format).format(Value);
                    tvUSD.setText("USD " + formattedDouble);
                } else if (CharCode.equals(EUR)){
                    Log.d(TAG, "I find EUR!!!");
                    String formattedDouble = new DecimalFormat(money_format).format(Value);
                    tvEUR.setText("EUR "  + formattedDouble);

                }
            }
        }
    }
}