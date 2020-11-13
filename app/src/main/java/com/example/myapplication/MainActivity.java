package com.example.myapplication;

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
    Date date;
    TextView mainDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.textView5);
        mainDate.setText(dateText);
    }


    public void OnClickBankomats(View view) {
        Intent intent = new Intent(this, firstActivity.class);
        startActivity(intent);
    }

    public void OnClickCurrency(View view) {
        Intent intent = new Intent(this, secondActivity.class);
        startActivity(intent);
    }


        public void showAlertDialogButtonClicked(View view)
        {

            // Create an alert builder
            AlertDialog.Builder builder
                    = new AlertDialog.Builder(this, R.style.mainLogin);

            // set the custom layout
            final View customLayout
                    = getLayoutInflater()
                    .inflate(
                            R.layout.custom_layout,
                            null);
            builder.setView(customLayout);

            // add a button
            builder
                    .setPositiveButton("Войти", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener(){
                                @Override
                                public void  onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();
                                }
                            });

            // create and show
            // the alert dialog
            AlertDialog dialog
                    = builder.create();
            dialog.show();
        }

        // Do something with the data
        // coming from the AlertDialog
        private void sendDialogDataToActivity(String data)
        {
            Toast.makeText(this,
                    data,
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }