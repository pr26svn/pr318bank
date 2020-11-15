package com.example.mobilebank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Date date;
    TextView mainDate;
    TextView usdText;
    TextView eurText;
    TextView view;
    BufferedReader BufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Метод отображения текущей даты */
        date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dataText=dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.data);
        mainDate.setText(dataText);


        usdText = (TextView)findViewById(R.id.course_usd);
        eurText = (TextView)findViewById(R.id.course_uer);

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

                        BufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        String line;
                        while ((line = in.readline()) != null) {
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

        NodeList valuteNodeList = document.getElementsByTagName("Sales");

        for (int i = 0; i < valuteNodeList.getLength(); i++) {
            if (valuteNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element valute = (Element) valuteNodeList.item(i);
                NodeList childNodes = valute.getChildNodes();

                String usd = "";
                String usd_dollar = "";
                String course_up = "";
                String course_down = "";
                ArrayList<Sales> salesArrayList = new ArrayList<>();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);
                        switch (childElement.getNodeName()) {
                            case "usd": {
                                usd = childElement.getTextContent();
                            }
                            break;
                            case "usd_dollar": {
                                usd_dollar = childElement.getTextContent();
                            }
                            break;
                            case "course_up": {
                                course_up = childElement.getTextContent();
                            }
                            break;
                            case "course_down": {
                                course_down = childElement.getTextContent();
                            }
                            break;
                        }

                        Sales val = new Sales(usd, usd_dollar, course_up, course_down);
                        salesArrayList.add(val);
                    }
                }

                for (int j = 0; j < salesArrayList.size(); j++) {
                    if (salesArrayList.get(j).getCourse_up() == "USD") {
                        usdText.setText(salesArrayList.get(j).getUsd());
                    } else if (salesArrayList.get(j).getCourse_up() == "EUR") {
                        eurText.setText(salesArrayList.get(j).getUsd());
                    }
                }
            }
        }

    }


    /* Метод перехода на окно с "Отделениями и банкоматами" */
    public void onClick(View view) {
        Intent intent = new Intent(this, Branches.class);
        startActivity(intent);
    }

    /* Метод перехода на окно с "Курсами валют" */
    public void BuyesPage(View view) {
        Intent intent = new Intent(this, BuyseAndSales.class);
        startActivity(intent);
    }



    /* Метод для отображение диалогового окна и реализация кнопок */
    public void forLogin(View view) {
        /* Создание диалогового окна */
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.builder);
        final View customLayout = getLayoutInflater().inflate(R.layout.loginin, null);
        builder.setView(customLayout);
        /* Реализация кнопок */
        builder.setPositiveButton("Войти", (dialog, which) -> dialog.cancel());
        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        AlertDialog dialog=builder.create();
        /* Вывод кастомного диалогового окна */
        dialog.show();
    }
}