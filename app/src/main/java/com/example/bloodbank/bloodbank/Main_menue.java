package com.example.bloodbank.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Main_menue extends AppCompatActivity {
     Button searchDonor ;
    Button details ;
    String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menue);
        searchDonor = (Button)findViewById(R.id.SearchDonor);
        details =  (Button)findViewById(R.id.MyDetails);
        Intent i = getIntent();
        Username = i.getStringExtra("Username");

        openSearch();
        openDetails();
    }

    private void openDetails() {
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menue.this ,My_details.class);
                intent.putExtra("Username",Username);
                startActivity(intent);
            }
        });
    }

    public void openSearch(){
        searchDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_menue.this ,Search_Donor.class);
                startActivity(intent);
            }
        });

    }
}
