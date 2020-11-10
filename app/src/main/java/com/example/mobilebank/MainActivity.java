package com.example.mobilebank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final Context context = this;
    LinearLayout btn_otdelenia, btn_currency;
    Button btn_enter;

    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_otdelenia = (LinearLayout) findViewById(R.id.btn_otdelenia);
        btn_currency = (LinearLayout) findViewById(R.id.btn_currency);
        btn_enter = (Button) findViewById(R.id.btn_enter);

        tvDate = (TextView) findViewById(R.id.tvDate);



        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        tvDate.setText(date);


        btn_otdelenia.setOnClickListener(this);
        btn_currency.setOnClickListener(this);
        btn_enter.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_otdelenia:
                 intent = new Intent(this, Otdelenia.class);
                 startActivity(intent);
                 break;
            case R.id.btn_currency:
                 Intent intent2 = new Intent(this, Currency.class);
                 startActivity(intent2);
                 break;
            case R.id.btn_enter:
                createDialog();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    void createDialog() {
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(context);
        View registrationView = li.inflate(R.layout.registration, null);


        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем registration.xml для нашего AlertDialog:
        mDialogBuilder.setView(registrationView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInputLogin = (EditText) registrationView.findViewById(R.id.input_login);
        final EditText userInputPassword = (EditText) registrationView.findViewById(R.id.input_password);


        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

            }
        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();
    }
}