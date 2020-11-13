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
import static space.dorzhu.test.R.style.Theme_AppCompat;


public class MainActivity extends AppCompatActivity {
    TextView mainDate;
    Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());//получаем дату
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.data);
        mainDate.setText(dateText);//записываем дату
        //new Process().execute();
    }


    public void onClick(View view) {

        Intent intent = new Intent(this, secondpage.class);//переходы на другие страницы
        startActivity(intent);
    }

    public void onClick2(View view) {

        Intent intent2 = new Intent(this, thirdactivity.class);//переход на другую стрицу
        startActivity(intent2);
    }


    public void Dialog(View view) {

        //вывоз диалогового окна
        AlertDialog.Builder bulBuilder=new AlertDialog.Builder(this,R.style.mainLogin);
        final View customLayout= getLayoutInflater().inflate(R.layout.activity_login,null);
        bulBuilder.setView(customLayout);
        bulBuilder
                .setPositiveButton("Ок", (dialog, which) -> dialog.cancel())
                .setNegativeButton("Отмена", (dialog, which) -> dialog.cancel());
        AlertDialog dialog=bulBuilder.create();
        dialog.show();


    }
}












