package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

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

    ImageButton btn_back;
    TextView curr_date;
    private static final String TAG = "myLogs";
    ListView lvMain;
    ArrayList<String> arrayList = new ArrayList<String>();

    ArrayList<Valuta> currensies = new ArrayList<Valuta>();
    CurrencyAdapter currencyAdapter;

    //Valuta vlt = new Valuta( "R1093","USD", "Американский доллар", 12.12, 54.34);

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        curr_date = (TextView)findViewById(R.id.curr_date);

                btn_back = (ImageButton)findViewById(R.id.btn_back2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        currencyAdapter = new CurrencyAdapter(this, currensies);


        ListView Currency_lv = (ListView) findViewById(R.id.Currency_lv);


        StringBuilder sb = new StringBuilder();
        tv = (TextView)findViewById(R.id.tvTest);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date(System.currentTimeMillis()));
                String query = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;

                HttpsURLConnection connection = null;
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()){
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                        String line;




                        while ((line = in.readLine()) != null){
                            Log.d(TAG, "Processing...");

                            sb.append(line);
                            sb.append("\n");

                        }

                        Log.d(TAG, "SB" + sb.toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                try {
                                    InputSource is = new InputSource(new StringReader(sb.toString()));
                                    is.setEncoding("Cp1251");
                                    Parsing(is);
                                    Currency_lv.setAdapter(currencyAdapter);
                                } catch (ParserConfigurationException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (SAXException e) {
                                    e.printStackTrace();
                                }
                                //tv.setText(sb.toString());

                            }
                        });
                    }else{
                        Log.d(TAG, "error");
                    }

                } catch (Throwable cause){
                    cause.printStackTrace();
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        });






    }

    private void Parsing(InputSource file_for_parsing) throws ParserConfigurationException, IOException, SAXException {
        Log.d(TAG, "Starting parsing");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document  = builder.parse(file_for_parsing);


        Element wrapper = (Element) document.getElementsByTagName("ValCurs").item(0);
        //String wrapper_id = wrapper.getAttribute("ID");

        NodeList nodeListValute = document.getElementsByTagName("Valute");


        for (int i = 0; i < nodeListValute.getLength(); i++){
            if (nodeListValute.item(i).getNodeType() == Node.ELEMENT_NODE){
                Log.d(TAG, "Прошел проверку");
                Element curr = (Element) nodeListValute.item(i);

                Valuta valuta = new Valuta(curr.getAttribute("ID"), "", "", "", "");

                NodeList childNotes = curr.getChildNodes();
                for (int j = 0; j < childNotes.getLength(); j++){
                    if (childNotes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNotes.item(j);

                        switch (childElement.getNodeName()){
                            case "CharCode":
                                valuta.SetName(childElement.getTextContent());
                                break;
                            case "Name":
                                valuta.SetFullName(childElement.getTextContent());
                                break;
                            case "Value":
                                valuta.SetBuy(childElement.getTextContent());
                                break;
                            case "Nominal":
                                valuta.SetSell(childElement.getTextContent());
                        }
                        Log.d(TAG, "Прошелся по свичу");
                    }
                }
                currensies.add(valuta);
                Log.d(TAG, "Добавил элемент в список");
            }

        }
        Log.d(TAG, "Ending parsing");

    }

}

