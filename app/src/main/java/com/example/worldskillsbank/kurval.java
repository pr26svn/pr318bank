package com.example.worldskillsbank;

import android.app.Dialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class kurval extends AppCompatActivity {
    URL url = null;
    Date date;
    TextView tv5;
    Dialog dialog;
    HttpsURLConnection connection = null;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kurval);

        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://www.cbr.ru/scripts/XML_daily.asp?date_req=09/11/2020");

        date = new Date();

        XmlPullParser xpp = getResources().getXml(R.xml.currencies);
        CurrencyResourceParser parser = new CurrencyResourceParser();
        if(parser.parse(xpp.toString()))
        {
            for(Currency prod: parser.getCurrencies()){
                Log.d("XML", prod.toString());
            }
        }
    }

    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "xml document: " + s);
            CurrencyResourceParser parser = new CurrencyResourceParser();
            if(s!=null && parser.parse(s))
            {
                for(Currency prod: parser.getCurrencies()){
                    Log.d("XML", prod.result());
                    // редактировать здесь
                }
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try{
                content = downloadXML(strings[0]);
            }
            catch (IOException e){
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            }
            return content;
        }

        private String downloadXML(String urlPath) throws IOException {
            StringBuilder xmlResult = new StringBuilder();
            BufferedReader reader = null;
            try {
                URL url = new URL(urlPath);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line=null;
                while ((line=reader.readLine()) != null) {
                    xmlResult.append(line);
                }
                return xmlResult.toString();
            } catch(MalformedURLException e) {
                Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage());
            } catch(IOException e) {
                Log.e(TAG, "downloadXML: IO Exception reading data: " + e.getMessage());
            } catch(SecurityException e) {
                Log.e(TAG, "downloadXML: Security Exception.  Needs permisson? " + e.getMessage());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
            return null;
        }
    }
}