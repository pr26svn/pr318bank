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

    public boolean parse(XmlPullParser xpp){
        boolean status = true;
        Money currentItem = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("Valute".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentItem = new Money();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("Valute".equalsIgnoreCase(tagName)){
                                items.add(currentItem);
                                inEntry = false;
                            }
                            else if("Name".equalsIgnoreCase(tagName)){
                                currentItem.setCurrency(textValue);
                            } else if("Value".equalsIgnoreCase(tagName)){
                                currentItem.setBuy(textValue);
                            }
                            else if("CharCode".equalsIgnoreCase(tagName)){
                                currentItem.setAbbreviation(textValue);
                            } /*else  if ("Value".equalsIgnoreCase(tagName)){
                                if (textValue == "77,1148"){
                                    currentItem.setDollars(textValue);
                                }
                            } else  if ("Value".equalsIgnoreCase(tagName)){
                                if (textValue == "90,8104"){
                                    currentItem.setEuros(textValue);
                                }
                            }*/
                        }
                        break;
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
