package com.example.bloodbank.bloodbank;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainPage extends AppCompatActivity {
        Timer timer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        android.support.v7.app.ActionBar bar=getSupportActionBar();
        bar.hide();;

        ImageView img= (ImageView)findViewById(R.id.imageView2);

        //Timer to move to next page

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent myintent=new Intent(MainPage.this,LoginPage.class);
                startActivity(myintent);
                finish();
            }
        }, 2500);
    }
}
