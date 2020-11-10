package com.example.mobilebank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Date date;
    TextView mainDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = new Date();
        DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dataText=dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.data);
        mainDate.setText(dataText);
    }


    public void onClick(View view) {
        Intent intent = new Intent(this, Branches.class);
        startActivity(intent);
    }


    public void BuyesPage(View view) {
        Intent intent = new Intent(this, BuyseAndSales.class);
        startActivity(intent);
    }


    public void dialog(View view) {
        String title="Авторизация";
        String message = "Введите Ваш логин и пароль";
        String pb="Войти";
        String nb="Отмена";

        AlertDialog.Builder a_builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        a_builder.setMessage(message)


                .setCancelable(false)
                .setPositiveButton(pb, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton(nb, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle(title);
        alert.show();
    }
}