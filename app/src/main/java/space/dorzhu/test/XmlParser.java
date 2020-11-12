package space.dorzhu.test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class XmlParser {

    private ArrayList<Course> courses;

    public XmlParser() {
        courses = new ArrayList<>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean parse(String xmlData) {
        boolean status = true;
        Course tempCourse = null;
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
                        if ("Valute".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            tempCourse = new Course();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
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
                                tempCourse.setValue(textValue);
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
