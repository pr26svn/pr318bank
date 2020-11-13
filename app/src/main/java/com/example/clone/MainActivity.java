package com.example.clone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Intent intent;
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


}