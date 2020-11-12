package com.example.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получение данных о текущей дате
        Date date;
        TextView setDate;
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        setDate = (TextView) findViewById(R.id.today);
        setDate.setText(dateText);
    }

    public void showAlertDialogButtonClicked(View view)
    {
        //Объявляю Builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this, R.style.loginStyle);

        //Подключаю отдельную activity
        final View loginActivity
                = getLayoutInflater()
                .inflate(
                        R.layout.activity_login_activity,
                        null);
        builder.setView(loginActivity);

        //Добавляю кнопку подтверждения
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText username = loginActivity.findViewById(R.id.username);
                sendDialogDataToActivity(username.getText().toString());
            }
        });

        //Добавляю кнопку отмены
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText password = loginActivity.findViewById(R.id.password);
                sendDialogDataToActivity(password.getText().toString());
            }
        });

        //Создаю и отображаю AlertDialog
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

    //Отображаю данные AlertDialog
    private void sendDialogDataToActivity(String data)
    {
        Toast.makeText(this,
                data,
                Toast.LENGTH_SHORT)
                .show();
    }
}