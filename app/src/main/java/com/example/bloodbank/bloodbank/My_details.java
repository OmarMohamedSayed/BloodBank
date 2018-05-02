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

public class My_details extends AppCompatActivity {
    SQLiteDatabase dbread;
    DatabaseHelper db;
    Cursor cursor;
    EditText editUsername , editPass,editName,editEmail,editAdd,editPhone ,editGov,editAge;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        Intent i = getIntent();
       String Username = i.getStringExtra("Username");

        editUsername = (EditText)findViewById(R.id.username);
        editPass = (EditText)findViewById(R.id.password);
        editName =(EditText)findViewById(R.id.name);
        editEmail = (EditText)findViewById(R.id.email);
        editAdd = (EditText)findViewById(R.id.add);
        editPhone = (EditText)findViewById(R.id.phone);
        editGov = (EditText)findViewById(R.id.gov);
        editAge = (EditText)findViewById(R.id.age);
        btnUpdate = (Button)findViewById(R.id.update);

        db = new DatabaseHelper(this);
        dbread = db.getReadableDatabase();
        cursor = dbread.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_1 + "=? " , new String[]{Username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                editUsername.setText(cursor.getString(0));
                editPass.setText(cursor.getString(1));
                editName.setText(cursor.getString(2));
                editEmail.setText(cursor.getString(3));
                editAdd.setText(cursor.getString(4));
                editPhone.setText(cursor.getString(5));
                editGov.setText(cursor.getString(6));
                editAge.setText(cursor.getString(7));
            } else {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

            }
        }
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = db.updateData(editUsername.getText().toString(), editPass.getText().toString(), editName.getText().toString(),editEmail.getText().toString(),editAdd.getText().toString(),editPhone.getText().toString(),editGov.getText().toString(),editAge.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(My_details.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(My_details.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}
