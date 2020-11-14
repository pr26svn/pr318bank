package com.example.clone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    BancomatsAdapter bancomatsAdapter;
    ArrayList<MainBancomats> mainBancomats = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branches_and_atms);

        listView = (ListView) findViewById(R.id.listBranches);



        new SecondActivity.newThreadTwo().execute();
        bancomatsAdapter = new BancomatsAdapter(this, R.layout.cusmom_list_branches, mainBancomats);


    }

    public void onClickBackBraches(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public class newThreadTwo extends AsyncTask<String,  Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            Document doc;
            try{

                doc =
                        Jsoup.connect("https://tnkfb.ru/adresa/gorod/omsk/").get();
                Elements elements = doc.select("div.bank_title");

                mainBancomats.clear();
                String value = "";

                for(Element element : elements){
                    String title = element.child(0).text();
                    mainBancomats.add(new MainBancomats(title, "", "", "Отделение"));


                }


            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            listView.setAdapter(bancomatsAdapter);
        }
    }


    /*
    AsyncTask.execute(new Runnable()  {
        @Override
        public void run(){

        }
    })
    */

}
