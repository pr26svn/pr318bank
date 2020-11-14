package com.example.mobilebank;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class SalesXmlParser {

    private ArrayList<Sales> sales;

    public SalesXmlParser() {
        sales = new ArrayList<>();
    }

    public ArrayList<Sales> getSales() {
        return sales;
    }

    public boolean parse(String xmlData) throws XmlPullParserException {
        boolean status = true;
        Sales currentSales = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("sales".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentSales = new Sales();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("sales".equalsIgnoreCase(tagName)) {
                                sales.add(currentSales);
                                inEntry = false;
                            } else if("flag".equalsIgnoreCase(tagName)) {
                                currentSales.getFlag();
                            } else if ("usd".equalsIgnoreCase(tagName)) {
                                currentSales.getUsd();
                            } else if ("usd_dollar".equalsIgnoreCase(tagName)) {
                                currentSales.getUsd_dollar();
                            }
                        }
                }

            }
        }
    }
}
