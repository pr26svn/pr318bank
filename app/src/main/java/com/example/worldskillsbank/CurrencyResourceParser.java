package com.example.worldskillsbank;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;

public class CurrencyResourceParser {

    private ArrayList<Currency> currencies;

    public CurrencyResourceParser() {
        currencies = new ArrayList<>();
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public boolean parse(String xmlData) {
        boolean status = true;
        Currency currentProduct = null;
        boolean inEntry = false;
        String textValue = "";
        int intValue = 0;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("Valute".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentProduct = new Currency();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        intValue = xpp.getAttributeCount();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("Valute".equalsIgnoreCase(tagName)) {
                                currencies.add(currentProduct);
                                inEntry = false;
                            } else if ("Name".equalsIgnoreCase(tagName)) {
                                currentProduct.setName(textValue);
                            } else if ("CharCode".equalsIgnoreCase(tagName)) {
                                currentProduct.setLittleName(textValue);
                            } else if ("Nominal".equalsIgnoreCase(tagName)) {
                                currentProduct.setNominal(Integer.parseInt(textValue));
                            } else if ("Value".equalsIgnoreCase(tagName)) {
                                currentProduct.setPrice(Double.parseDouble(textValue));
                            } else if ("NumCode".equalsIgnoreCase(tagName)) {
                                currentProduct.setNumCode(Integer.parseInt(textValue));
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}