package com.example.bloodbank.bloodbank;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    SQLiteDatabase dbread;
    DatabaseHelper db;
    Button reg,log;
    EditText user,passw;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        db = new DatabaseHelper(this);
        dbread = db.getReadableDatabase();


        user = (EditText)findViewById(R.id.username);
        passw = (EditText)findViewById(R.id.passwo);
        reg = (Button)findViewById(R.id.regBtn);
        log = (Button)findViewById(R.id.loginBtn);
        onclickReg();
        onclickLog();

    }

    public void onclickReg() {
         reg.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(LoginPage.this ,Registration.class);
                 startActivity(intent);
             }
         });
     }
    public void onclickLog() {
        log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email = user.getText().toString();
                String pass = passw.getText().toString();

                cursor = dbread.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_1 + "=? AND " + DatabaseHelper.COL_2 + "=?", new String[]{Email, pass});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Intent intent = new Intent(LoginPage.this ,Main_menue.class);
                        intent.putExtra("Username",Email);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Username or Password Not Correct", Toast.LENGTH_SHORT).show();

                    }
                }
                cursor.close();
                dbread.close();

            }
        });
    }


}

