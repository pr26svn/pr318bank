package space.dorzhu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class custom_list extends AppCompatActivity {

    public custom_list(thirdactivity thirdactivity, ArrayList courses) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);


    }
}