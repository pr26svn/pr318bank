package com.example.clone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
    Intent intent;
    TextView txtDollar;
    TextView txtEuro;
    String dollar;
    String dollarResult;
    String euro;
    String euroResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date date;

        TextView mainDate;

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.textView6);
        mainDate.setText(dateText);


        txtDollar = (TextView) findViewById(R.id.txtDollar);
        txtEuro = (TextView) findViewById(R.id.txtEuro);

        new MainActivity.newThreadOne().execute();
    }

    public void onClickFirst(View view) {
        intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void onClickCurancy(View view) {
        intent = new Intent(this, mainCurrency.class);
        startActivity(intent);
    }
    public void showAlertDialogButtonClicked(View view)
    {

        AlertDialog.Builder builder
                = new AlertDialog.Builder(this, R.style.mainLogin);

        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.custom_layout,
                        null);
        builder.setView(customLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText username = customLayout.findViewById(R.id.mainUsername);
                sendDialogDataToActivity(username.getText().toString());
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText password = customLayout.findViewById(R.id.mainPassword);
                sendDialogDataToActivity(password.getText().toString());
            }
        });

        AlertDialog dialog
            = builder.create();
        dialog.show();
    }
    private void sendDialogDataToActivity(String data)
    {
        Toast.makeText(this,
                data,
                Toast.LENGTH_SHORT)
                .show();
    }

    public class newThreadOne extends AsyncTask<String,  Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            double oDollar;
            double oEuro;
            Document doc;
            int flag = 0;
            try{
                doc = Jsoup.connect("https://www.profinance.ru/currency_usd.asp").get();
                Elements elements = doc.select("tr.stat");
                String value;
                for(Element element : elements){
                    if(flag > 0){
                        value = element.child(3).text();

                        if(flag == 5){
                            dollar = value;
                            oDollar = Double.parseDouble(dollar);
                            dollarResult = String.format("%.2f", oDollar);
                        }else if(flag == 6){
                            euro = value;
                            oEuro= Double.parseDouble(euro);
                            euroResult = String.format("%.2f", oEuro);

                        }

                    }
                    flag++;
                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            txtDollar.setText(dollarResult);
            txtEuro.setText(euroResult);
        }
    }

}