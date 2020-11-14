package com.example.bank;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class ParserTest {

    private ArrayList<Money> items;

    public ParserTest(){
        items = new ArrayList<>();
    }

    public ArrayList<Money> getItems(){
        return items;
    }

    //Парсинг .xml файла
    public boolean parse(XmlPullParser xpp){
        boolean status = true;
        Money currentItem = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            //Задаём тип события и начинаем цикл до конца документа
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    //Если мы нашли начальный тэг, то мы находимся в начале документа и создаём объект класса, в который будем записывать результаты парсинга
                    case XmlPullParser.START_TAG:
                        if("Valute".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentItem = new Money();
                        }
                        break;
                        //Если тэг содержит какое-нибудь значение, то записываем его в переменную textValue
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                        // Если мы нашли конечный тэг документа, то начинаем запись результатов парсинга
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("Valute".equalsIgnoreCase(tagName)){
                                items.add(currentItem);
                                inEntry = false;
                            }
                            //Если имя тэга = Name, то записываем название валюты
                            else if("Name".equalsIgnoreCase(tagName)){
                                currentItem.setCurrency(textValue);
                                //Если имя тэга = Value, то записываем значение, содержащееся в тэге (Стоимость покупки/продажи)
                            } else if("Value".equalsIgnoreCase(tagName)){
                                currentItem.setBuy(textValue);
                            }
                            //Если имя тэга = CharCode, то записываем аббревиатуру валюты
                            else if("CharCode".equalsIgnoreCase(tagName)){
                                currentItem.setAbbreviation(textValue);
                            }
                        }
                        break;
                        // Если ничего из вышеперечисленного не найдено, то перемещаемся к следующему тэгу
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }

}
