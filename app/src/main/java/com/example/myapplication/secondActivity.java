package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

public class secondActivity extends AppCompatActivity {
    TextView txtXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        String blokXml="";
        txtXml = (TextView) findViewById(R.id.textView13);
        XmlPullParser xpp = getResources().getXml(R.xml.currency);
        currencyParser parser = new currencyParser();
        if (parser.parse(xpp))
        {
            for (Currency currencyParser: parser.getCurrencies()){
                blokXml = blokXml + currencyParser.toString() + "\n";
            }
            txtXml.setText(blokXml);
        }
    }

    public void finish(View view) {
        finish();
    }
}
