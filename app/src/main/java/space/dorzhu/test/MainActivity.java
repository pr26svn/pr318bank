package space.dorzhu.test;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import static space.dorzhu.test.R.style.AppCompatAlertDialogStyle;


public class MainActivity extends AppCompatActivity {
    TextView mainDate;
    Date date;

     /*   URL url;

    {
        try {
            url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=09/11/2020");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    HttpsURLConnection connection;

    {
        try {
            connection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      */







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.data);
        mainDate.setText(dateText);
        //new Process().execute();
    }


    public void onClick(View view) {

        Intent intent = new Intent(this, branches.class);
        startActivity(intent);
    }

    public void onClick2(View view) {

        Intent intent = new Intent(this, thirdactivity.class);
        startActivity(intent);
    }


    public void Dialog(View view) {

        AlertDialog.Builder a_bulder= new AlertDialog.Builder(MainActivity.this, AppCompatAlertDialogStyle);
        a_bulder.setMessage("Введите ваш логин и пароль")
                .setCancelable(false)
                .setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.cancel();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert=a_bulder.create();
        alert.setTitle("Авторизация");
        alert.show();

    }
}


/*class Process extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] objects) {

        return null;
    }
}*/









