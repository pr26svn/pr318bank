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

        try{
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("Valute".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentCurrency = new Currency();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("Valute".equalsIgnoreCase(tagName)) {
                                currencies.add(currentCurrency);
                                inEntry = false;
                            } else if ("NumCode".equalsIgnoreCase(tagName)) {
                                currentCurrency.setNumCode(textValue);
                            } else if ("CharCode".equalsIgnoreCase(tagName)) {
                                currentCurrency.setCharCode(textValue);
                            } else if ("Nominal".equalsIgnoreCase(tagName)) {
                                currentCurrency.setNominal(textValue);
                            } else if ("Name".equalsIgnoreCase(tagName)) {
                                currentCurrency.setName(textValue);
                            } else if ("Value".equalsIgnoreCase(tagName)) {
                                currentCurrency.setValue(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();

            }
            //valuta
        } catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
