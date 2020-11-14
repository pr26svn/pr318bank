package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class Exchange_rates extends AppCompatActivity {

    //Обработка нажатия по кнопке "Главный экран"
    public void  return_click(View view){
        Intent intent = new Intent(Exchange_rates.this,MainActivity.class);
        startActivity(intent);
    }


    //Заполнение ListView информацией о курсах валют с помощью парсинга
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rates);



        ListView listView = findViewById(R.id.list_view2);
        ArrayList<Money> arrayList = new ArrayList<>();

        XmlPullParser xpp = getResources().getXml(R.xml.xml_daily);
        ParserTest parser = new ParserTest();
        if(parser.parse(xpp))
        {
            for(Money money: parser.getItems()){
                arrayList.add(money);
            }
        }

        adapter_money adapter_money = new adapter_money(this,R.layout.list2,arrayList);
        listView.setAdapter(adapter_money);
    }
}