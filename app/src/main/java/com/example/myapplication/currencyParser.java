package com.example.myapplication;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import com.example.myapplication.Currency;

public class currencyParser {
    private ArrayList<Currency> currencies;
    public currencyParser(){
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

        try {
            int evenType = xpp.getEventType();
            while (evenType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();
                switch (evenType) {
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
                                }else if ("NumCode".equalsIgnoreCase(tagName)) {
                                    currentCurrency.setNumCode(textValue);
                                }else if ("CharCode".equalsIgnoreCase(tagName)) {
                                    currentCurrency.setCharCode(textValue);
                                }else if ("Nominal".equalsIgnoreCase(tagName)) {
                                    currentCurrency.setNominal(textValue);
                                }else if ("Name".equalsIgnoreCase(textValue)) {
                                    currentCurrency.setName(textValue);
                                }else if ("Value".equalsIgnoreCase(tagName)) {
                                    currentCurrency.setValue(textValue);
                                }
                            }
                            break;
                    default:
                }
                evenType = xpp.next();
            }
        }catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
