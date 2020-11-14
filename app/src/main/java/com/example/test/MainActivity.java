package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        TextView Date_txt = findViewById(R.id.textView9);
        Date_txt.setText((format.format(new Date())));

        Button button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = findViewById(R.id.Et);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.builder);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.activity_log_in, null));
                builder.setCancelable(false);

                builder.setPositiveButton(R.string.button5, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (editText == null){
                            Toast.makeText(getApplicationContext(),"Поле для ввода пустое",Toast.LENGTH_SHORT);
                        }
                    }
                });
                builder.setNegativeButton(R.string.button4, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });


    }

    public void ATM(View view) {
        Intent intent = new Intent(MainActivity.this, Bank.class);
        startActivity(intent);    }

    public void valute(View view) {
        Intent intent = new Intent(MainActivity.this, Valute.class);
        startActivity(intent);
    }
}


