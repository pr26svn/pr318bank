package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class bankomat extends AppCompatActivity {
    Intent intent;
    String [] bankomats = {"asdsadasdadasdasd", "sdsdsd"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankomat);
        ListView list = (ListView) findViewById(R.id.listBank);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bankomats);
        list.setAdapter(adapter);
    }
    public void onClick_Home(View view){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
