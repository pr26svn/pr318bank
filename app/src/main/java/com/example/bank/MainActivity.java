package com.example.bank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button enter, branches_change, rate_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton () {

        enter = findViewById(R.id.enter_button);
        branches_change = findViewById(R.id.branches);
        rate_check = findViewById(R.id.exchange_rates);

        enter.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        AlertDialog.Builder login_builder = new AlertDialog.Builder(MainActivity.this);
                        login_builder.setCancelable(false);
                        login_builder.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        login_builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog login = login_builder.create();
                        login.setTitle("Авторизация");
                        login.setMessage("Введите Ваш логин и пароль");
                        login.show();
                    }
                }
        );

        branches_change.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(".ActivityBranchesInfo");
                        startActivity(intent);
                    }
                }
        );

        rate_check.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent(".activity_exchange_rate");
                        startActivity(intent);
                    }
                }
        );
    }
}