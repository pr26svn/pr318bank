package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Date date;
    Intent intent;
    TextView mainDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.data);
        mainDate.setText(dateText);
    }



    public void StartBranchAtm(View view) {
        Intent intent = new Intent(MainActivity.this, branches_atm.class);
        startActivity(intent);
    }

    public void StartKursBalut(View view) {
        Intent intent = new Intent(MainActivity.this, KursValut.class);
        startActivity(intent);
    }


    public void AlertDialog(View view) {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setMessage("Введите ваш логин и пароль")

                .setCancelable(false)
                .setPositiveButton("Вход", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Авторизация");
        alert.show();
    }
}