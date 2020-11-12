package space.milko.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        ListView listView = findViewById(R.id.listView);
//        LinearLayout linearLayout2 = findViewById(R.id.linearLayout1);
//        ArrayAdapter<LinearLayout> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, linearLayout2);
//        listView.setAdapter(adapter);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        linearLayout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}