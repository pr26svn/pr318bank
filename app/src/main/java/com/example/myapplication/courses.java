package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class courses extends AppCompatActivity {

    Intent intentt;
    MainActivity m;
    TextView data;
    ListView listView;
    String dateText;
    public CustomAdapter adapter;
    ArrayList<Course> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        listView = findViewById(R.id.ListCourse);

        list = (ArrayList<Course>) getIntent().getSerializableExtra("list");//получаем из объекта интент возвращаемый параметр
        adapter = new CustomAdapter(this, list);//заполняем адаптер
        listView.setAdapter(adapter);//заполняем листвью
        data = findViewById(R.id.dataa);//для даты
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateText = dateFormat.format(date);
        data.setText(dateText);
    }


    public void onClick_Home(View view) {
        intentt = new Intent(this, MainActivity.class);
        startActivity(intentt);
    }

}