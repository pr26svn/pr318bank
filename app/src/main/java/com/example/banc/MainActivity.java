package com.example.banc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity<TextView> extends AppCompatActivity {

    android.widget.TextView textViewDate;
    LinearLayout searchBankButton;
    LinearLayout viewExchangeRatesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = findViewById(R.id.date);
        searchBankButton = findViewById(R.id.buttonBranchesAndATMs);
        viewExchangeRatesButton = findViewById(R.id.buttonExchangeRates);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        textViewDate.setText(dateFormat.format(new Date()));

        searchBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchBank.class);
                startActivity(intent);
            }
        });
        viewExchangeRatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchBank.class);
                startActivity(intent);
            }
        });

        /*AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                    URL urlExchangeRates = new URL(String.format("http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s", date.format(new Date())));
                    HttpsURLConnection connection = (HttpsURLConnection) urlExchangeRates.openConnection();
                    InputStream responseBody = connection.getInputStream();
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    jsonReader.beginObject(); // Start processing the JSON object
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String key = jsonReader.nextName(); // Fetch the next key
                        if (key.equals("organization_url")) { // Check if desired key
                            // Fetch the value as a String
                            String value = jsonReader.nextString();



                            break; // Break out of the loop
                        } else {
                            jsonReader.skipValue(); // Skip values of other keys
                        }
                    }
                    jsonReader.close();
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    public void signin_Click(View view) {
        final EditText editText = findViewById(R.id.username);
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.DialogTheme));
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_signin, null))
                .setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        if (editText == null) {
                            Toast.makeText(getApplicationContext(),"Ошибка. Вы ничего не ввели", Toast.LENGTH_SHORT);
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }
}