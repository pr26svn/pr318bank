package com.example.myapplication;

import com.example.myapplication.Course;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class XmlParser {
    private ArrayList<Course> courses;//для хранения объектов

    public XmlParser() {
        courses = new ArrayList<>();
    }//конструктор

    public ArrayList<Course> getCourses() {
        return courses;
    }//возвращает список парс-объектов

    public boolean parse(String xmlData) {//метод парсера
        boolean status = true;// успешно\провал
        Course tempCourse = null;//временный объект для заполнения списка
        boolean inEntry = false;// парсим элемент?
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();// представляем элемент в виде событий и тегов
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();//начинаем разбирать

            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();//какое событие произошло
            while (eventType != XmlPullParser.END_DOCUMENT) {//пока не конец докумета проходим xml в цикле

                String tagName = xpp.getName();//имя тега
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("Valute".equalsIgnoreCase(tagName)) {//тег который сигнализирует о начале информации о валюте
                            inEntry = true;
                            tempCourse = new Course();//новый объект
                        }
                        break;
                    case XmlPullParser.TEXT://читаем текст
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG://разбираем остальные теги
                        if(inEntry) {
                            if ("Valute".equalsIgnoreCase(tagName)) {
                                courses.add(tempCourse);
                                inEntry = false;
                            } else if ("NumCode".equalsIgnoreCase(tagName)) {
                                tempCourse.setNumCode(textValue);
                            } else if ("CharCode".equalsIgnoreCase(tagName)) {
                                tempCourse.setCharCode(textValue);
                            } else if ("Nominal".equalsIgnoreCase(tagName)) {
                                tempCourse.setNominal(textValue);
                            } else if ("Name".equalsIgnoreCase(tagName)) {
                                tempCourse.setName(textValue);
                            } else if ("Value".equalsIgnoreCase(tagName)) {
                                tempCourse.setValue(Double.parseDouble(textValue.replace(',','.')));
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

