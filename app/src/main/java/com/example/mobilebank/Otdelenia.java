package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Otdelenia extends AppCompatActivity implements View.OnClickListener {

    String toast_result;

    ImageButton btn_back;

    ArrayList<Otdelenie> otdelenies = new ArrayList<Otdelenie>();
    BoxAdapter boxAdapter;

    Otdelenie otd = new Otdelenie("г. Москва, ул. Вавилова, д. 4", "00:00-00:00", "Работает", "Банкомат");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otdelenia);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, otdelenies);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);

        otdelenies.add(new Otdelenie("г. Омск", "08:00-20:00", "Не работает", "Отделение"));

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_for_otdelenia,
                        (ViewGroup) findViewById(R.id.custom_toast_container));


                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });


    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 19; i++) {
            otdelenies.add(otd);
        }
    }



    @Override
    public void onClick(View v) {
        finish();
    }
}