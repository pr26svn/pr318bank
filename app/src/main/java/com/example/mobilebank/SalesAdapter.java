package com.example.mobilebank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.io.StringReader;
import java.util.ArrayList;

public class SalesAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Sales> objects;

    SalesAdapter(Context context, ArrayList<Sales> otdelenies) {
        ctx = context;
        objects = otdelenies;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //меняю layout на свой собственный
            view = lInflater.inflate(R.layout.list_row_sales, viewGroup, false);
        }

        Sales p = getValute(i);

        // заполняем View в пункте списка данными из отделения: адресс, работает ли, часы работы
        ((TextView) view.findViewById(R.id.usd)).setText(p.getUsd());
        ((TextView) view.findViewById(R.id.usd_dollar)).setText(p.getUsd_dollar());
        ((TextView) view.findViewById(R.id.course_up)).setText(p.getCourse_up());
        ((TextView) view.findViewById(R.id.course_down)).setText(p.getCourse_down());
        return view;
    }

    Sales getValute(int position) {
        return ((Sales) getItem(position));
    }
}





    /*
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
                            } else if ("flag".equalsIgnoreCase(tagName)) {
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

     */

