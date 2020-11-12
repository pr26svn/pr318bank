package com.example.clone;

import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import static java.lang.System.in;
import static java.lang.System.out;

public class ThirthActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curancy);
        String blockXml = "";
        listView = (ListView) findViewById(R.id.listCurrancy);
        XmlPullParser xpp = getResources().getXml(R.xml.currancy);
        CurrencyParser currencyParser = new CurrencyParser();
        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(this, R.layout.curancy,
                currencyParser.currencies);

            if (currencyParser.parse(xpp)) {

        }

    }
}