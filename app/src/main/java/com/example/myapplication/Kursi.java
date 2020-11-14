package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class Kursi extends AppCompatActivity {

    //Обработка нажатия по кнопке "Главный экран"
    public void  return_click(View view){
        Intent intent = new Intent(Kursi.this, MainActivity.class);
        startActivity(intent);
    }


    //Заполнение ListView информацией о курсах валют с помощью парсинга
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kursi);



        ListView listView = findViewById(R.id.op);
        ArrayList<Dengi> arrayList = new ArrayList<>();

        XmlPullParser xpp = getResources().getXml(R.xml.xml_daily);
        ParserTest parser = new ParserTest();
        if(parser.parse(xpp))
        {
            for(Dengi money: parser.getItems()){
                arrayList.add(money);
            }
        }

        adapter_Dengi adapter_money = new adapter_Dengi(this,R.layout.list2,arrayList);
        listView.setAdapter(adapter_money);
    }
}