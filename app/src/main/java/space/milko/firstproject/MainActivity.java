package space.milko.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Date currentDate = new Date();

//class CourseQueryTask extends AsyncTask<>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        TextView date = findViewById(R.id.textView2);
        date.setText(dateText);
        LinearLayout linearLayout_main  = (LinearLayout) findViewById(R.id.layout_main);
        linearLayout_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
//        DownloadData downloadData = new DownloadData();
//        downloadData.execute("http://www.cbr.ru/scripts/XML_daily.asp?date_req=10/11/2020");

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                URL generatedUrl = null;
//                try {
//                    generatedUrl = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=10/11/2020");
//
//                    HttpURLConnection connectionForCourse = (HttpURLConnection) generatedUrl.openConnection();
//                    connectionForCourse.setRequestProperty("User-Agent", "bank");
//                    if(connectionForCourse.getResponseCode()==200){
//                        InputStream responseBody = connectionForCourse.getInputStream();
//                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
//                        xmlReader = new XmlPullParser (responseBodyReader);
//
//                    }else{
//
//                    }
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }




    }