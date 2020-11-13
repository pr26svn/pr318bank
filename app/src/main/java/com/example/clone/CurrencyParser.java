package com.example.clone;

import android.database.CursorIndexOutOfBoundsException;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import com.example.clone.Currency;

public class CurrencyParser {
    public ArrayList<Currency> currencies;
    public CurrencyParser(){
        currencies = new ArrayList<>();
    }
    public ArrayList<Currency> getCurrencies(){
        return currencies;
    }
    public boolean parse(XmlPullParser xpp){
        boolean status = true;
        Currency currentCurrency = null;
        boolean inEntry = false;
        String textValue = "";
        String name = "";
        String charCode = "";
        String value = "";
        int flag = 0;
        try{
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("Valute".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("Valute".equalsIgnoreCase(tagName)) {
                                currencies.add(new Currency(R.drawable.usa, name, charCode, value));
                                inEntry = false;
                            } else if ("CharCode".equalsIgnoreCase(tagName)) {
                                charCode = textValue;
                            } else if ("Name".equalsIgnoreCase(tagName)) {
                                name = textValue;
                            } else if ("Value".equalsIgnoreCase(tagName)) {
                                value = textValue;
                            }
                        }
                        break;
                    default:
                }

                eventType = xpp.next();

            }
        } catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
