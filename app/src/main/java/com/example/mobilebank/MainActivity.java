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

    public void forLogin(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.builder);
        final View customLayout = getLayoutInflater().inflate(R.layout.loginin, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Войти", (dialog, which) -> dialog.cancel());
        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}