package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Date date;
    TextView mainDate;
    String eur;
    String usd;
    String eurResult;
    String usdResult;
    TextView txtUsd;
    TextView txtEur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.textView5);
        mainDate.setText(dateText);

        txtUsd = (TextView) findViewById(R.id.textView6);
        txtEur = (TextView) findViewById(R.id.textView7);

        new MainActivity.newThreadOne().execute();
    }

    public class newThreadOne extends AsyncTask<String,  Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            double oUsd;
            double oEur;
            Document doc;
            int flg = 0;
            try {
                doc = Jsoup.connect("https://www.profinance.ru/currency_usd.asp%22").get();
                Elements elements = doc.select("tr.stat");
                String value;
                for (Element element : elements) {
                    if (flg > 0) {
                        value = element.child(3).text();

                        if (flg == 5) {
                            usd = value;
                            oUsd = Double.parseDouble(usd);
                            usdResult = String.format("%.2f", oUsd);
                        } else if (flg == 6) {
                            eur = value;
                            oEur = Double.parseDouble(eur);
                            eurResult = String.format("%.2f", oEur);

                        }

                    }
                    flg++;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            txtUsd.setText(usdResult);
            txtEur.setText(eurResult);
        }
    }

    public void OnClickBankomats(View view) {
        Intent intent = new Intent(this, firstActivity.class);
        startActivity(intent);
    }

    public void OnClickCurrency(View view) {
        Intent intent = new Intent(this, secondActivity.class);
        startActivity(intent);
    }


        public void showAlertDialogButtonClicked(View view)
        {

            // Create an alert builder
            AlertDialog.Builder builder
                    = new AlertDialog.Builder(this, R.style.mainLogin);

            // set the custom layout
            final View customLayout
                    = getLayoutInflater()
                    .inflate(
                            R.layout.custom_layout,
                            null);
            builder.setView(customLayout);

            // add a button
            builder
                    .setPositiveButton("Войти", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener(){
                                @Override
                                public void  onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            });

            // create and show
            // the alert dialog
            AlertDialog dialog
                    = builder.create();
            dialog.show();
        }

        // Do something with the data
        // coming from the AlertDialog
        private void sendDialogDataToActivity(String data)
        {
            Toast.makeText(this,
                    data,
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }