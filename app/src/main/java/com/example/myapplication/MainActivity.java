package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public boolean error = false;
    Intent intent;
    public String dateText;
    Dialog dialog;

    TextView USD;//хранение предпросмотра usd
    TextView EUR;

    public XmlParser parser = new XmlParser();//объект парсера
    ArrayList<Course> courseArrayList = new ArrayList<>();//лист с парсер-объектами

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = findViewById(R.id.data);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateText = dateFormat.format(date);//для отображения даты
        view.setText(dateText);
        //dialog = new Dialog(MainActivity.this);
        dialog = new Dialog(MainActivity.this);
        USD = findViewById(R.id.usd);
        EUR = findViewById(R.id.eur);
        DownloadData downloadData = new DownloadData();//создаем новый объект
        downloadData.execute("https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateText);//начинаем работу в фоне с передачей строгоквого параметра (url)

    }
    /*public void showDialog(String message){
        dialog.setContentView(R.layout.dialog_view);
        TextView text = (TextView) dialog.findViewById(R.id.dialogTextView);
        text.setText(message);
        dialog.show();
    }*/
    public void onClick_Bankomats(View view){
        intent = new Intent(this, bankomat.class);
        startActivity(intent);
    }
    public void onClick_Courses(View view){
        intent = new Intent(this, courses.class);
        intent.putExtra("list", courseArrayList);//передаем парсер-объекты
        startActivity(intent);
    }

    public void onClick_Login(View view){
        dialog.setContentView(R.layout.login_dialogg);
        dialog.show();
    }


    public void fucnk(){
        courseArrayList = parser.getCourses();//заполняем парсер-объектами список с помощью функции
        USD.setText(courseArrayList.get(10).getValue());
        EUR.setText(courseArrayList.get(11).getValue());
    }

    private class DownloadData extends AsyncTask<String, Void, String>{//класс с фоновыми процессами
        private static final String TAG = "DownloadFile";//для логов

        @Override
        protected void onPostExecute(String s){//происходит после окончания фонового процесса
            super.onPostExecute(s);
            Log.d(TAG, "xml document: "+s);
            if(s!=null && parser.parse(s)){
                fucnk();//функция для заполнения листа
            }
        }
        @Override
        protected String doInBackground(String... strings){//работа в фоне
            String content = null;
            try{
                content = downloadXML(strings[0]);//возвращает прочитанный xml
            }catch (IOException ex){
                Log.e(TAG, "downloadXML: IO Exception reading data: " + ex.getMessage());
            }
            return content;
        }
        private String downloadXML(String urlPath) throws IOException{//считывает xml
            StringBuilder xmlResult = new StringBuilder();
            BufferedReader reader = null;
            try{
                URL url = new URL(urlPath);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();//подключается
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));//считывает ответ и устанавливает кодировку
                String line = null;
                while ((line = reader.readLine())!=null){
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            }catch (MalformedURLException e){
                Log.e(TAG, "downloadXML: InvalidUrl" + e.getMessage());
            }catch(IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch(SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
            }
            finally {
                if(reader != null){
                    reader.close();
                }
            }
            return null;
        }
    }

}

