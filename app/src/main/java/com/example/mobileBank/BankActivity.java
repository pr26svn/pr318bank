package com.example.mobileBank;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class BankActivity extends AppCompatActivity {

    private ListView listViewBank;

    // список банков
    private ArrayList<Bank> banks;

    // адаптер для заполнения ListView списком банков banks
    private BankAdapter bankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        listViewBank = findViewById(R.id.listViewAtms);

        banks = new ArrayList<>();

        // выполнение запроса и парсинг в фоновом потоке
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // объявление соединения
                HttpsURLConnection connection = null;
                try {
                    // получение данных о банках города Донецк
                    URL url = new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=Донецк");

                    // создание соединения с get запросом и подключение
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.connect(); // запуск соединения

                    // проверка результата соединения
                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        // получение данных из ответа
                        InputStreamReader isr = new InputStreamReader(connection.getInputStream());

                        // Парсинг
                        parser(isr);

                        isr.close();
                        // передача данных в ListView в основном потоке
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // заполнение адаптера и вывод данных в ListView
                                bankAdapter = new BankAdapter(BankActivity.this, R.layout.list_item_bank, banks);
                                listViewBank.setAdapter(bankAdapter);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
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
     * метод для парсинга json файла
     */
    private void parser(InputStreamReader file) throws IOException, ParseException {
        // получение объекта с помощью JSONSimple
        Object object = new JSONParser().parse(file);
        JSONObject jo = (JSONObject) object;

        // получаем массив devices, в нем хранится вся информация о банках
        JSONArray devices = (JSONArray) jo.get("devices");

        // просмотр всех элементов
        for (Object obj : devices) {
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject tw = (JSONObject) jsonObject.get("tw");
            // получение адреса, типа и времени работы
            String address = jsonObject.get("fullAddressRu").toString();
            address = address.substring(address.indexOf("город"))
                    .replace("город", "г.")
                    .replace("проспект", "пр-т")
                    .replace("улица", "ул.")
                    .replace("дом", "д.")
                    .replace(",", ", ");
            String type = jsonObject.get("placeRu").toString();
            // получение дня недели, а по нему время работы
            String dayOfWeek = new SimpleDateFormat("EEE", Locale.US).format(new Date()).toLowerCase();
            String workingHours = tw.get(dayOfWeek).toString();

            // создание объекта с полученными данными и добавление в список банков
            Bank bank = new Bank(address, type, workingHours);
            banks.add(bank);
        }
    }


    /**
     * метод нажатия на кнопку "Главное меню", выход на стартовый экран
     */
    public void returnBack_Click(View view) {
        finish();
    }
}