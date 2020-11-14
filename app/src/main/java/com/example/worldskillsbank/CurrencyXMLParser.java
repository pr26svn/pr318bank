package com.example.worldskillsbank;

import java.io.StringReader;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class CurrencyXMLParser {

    // создание списка экземпляров класса
    private final ArrayList<Currency> currencies;

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    // создание конструктора класса
    public CurrencyXMLParser() {
        currencies = new ArrayList<>();
    }

    // создание функции парсинга
    public boolean parse(String xmlData) {

        // создание переменных для парсинга
        boolean status = true;
        Currency currentCurrency = null;
        boolean inEntry = false;
        String textValue = "";

        // обработчик исключений
        try {

            // создание переменной для работы с xml файлом
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));

            // переменная с первым событием
            int eventType = xpp.getEventType();

            // цикл выполняется пока мы не дойдем до конца документа
            while (eventType != XmlPullParser.END_DOCUMENT) {

                // переменной tagName присваивается значение названия тега
                String tagName = xpp.getName();

                // обработчик условий
                switch (eventType) {

                    // если тег открывающий
                    case XmlPullParser.START_TAG:

                        // если это тег Valute, то создается новый экземпляр класса
                        if ("Valute".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentCurrency = new Currency();
                        }
                        break;

                    // если это текст, то textValue принимает значение текста
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        if (textValue == "USD"){

                        }
                        break;

                    // если тег закрывающий
                    case XmlPullParser.END_TAG:
                        if (inEntry) {

                            // если это тег Valute, то в список экземпляров класса добавляется текущий экземпляр класса, если любой другой, то текущий экземпляр класса заполняется
                            if ("Valute".equalsIgnoreCase(tagName)) {
                                currencies.add(currentCurrency);
                                inEntry = false;
                            } else if ("CharCode".equalsIgnoreCase(tagName)) {
                                currentCurrency.setLittleName(textValue);
                            } else if ("Name".equalsIgnoreCase(tagName)) {
                                currentCurrency.setName(textValue);
                            } else if ("Value".equalsIgnoreCase(tagName)) {
                                currentCurrency.setPrice(textValue);
                            }
                        }
                        break;
                    default:
                }

                // переход к следующему событию
                eventType = xpp.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        // возвращение значения статуса
        return status;
    }
}