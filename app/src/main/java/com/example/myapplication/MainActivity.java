package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public boolean error = false;
    Intent intent;

    URL url;
    Dialog dialog;
    HttpsURLConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = findViewById(R.id.data);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        view.setText(dateText);
        dialog = new Dialog(MainActivity.this);
        try {
            url = new URL("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "my-rest-app");
        } catch (Exception e) {
            showDialog(e.getMessage());
        }

        // new Process().execute();
    }
    public void showDialog(String message){
        dialog.setTitle("Ошибка");
        dialog.setContentView(R.layout.dialog_view);
        TextView text = (TextView) dialog.findViewById(R.id.dialogTextView);
        text.setText(message);
        dialog.show();
    }
    public void onClick_Bankomats(View view){
        intent = new Intent(this, bankomat.class);
        startActivity(intent);
    }
    public void onClick_Courses(View view){
        intent = new Intent(this, courses.class);
        startActivity(intent);
    }


}
class Process extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] objects) {

        return null;
    }
}