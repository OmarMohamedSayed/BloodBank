package com.example.bloodbank.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class Registration extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editUsername , editPass,editName,editEmail,editAdd,editPhone ,editGov,editAge;
    Button btnAddData , btnLogIn;
    RadioGroup radioblood ,radioGen , radioDon;
    RadioButton radioButtonblood,radioButtonGen,radioButtonDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDb = new DatabaseHelper(this);

        editUsername = (EditText)findViewById(R.id.username);
        editPass = (EditText)findViewById(R.id.Pass);
        editName = (EditText)findViewById(R.id.Name);
        editAdd = (EditText)findViewById(R.id.Add);
        editPhone = (EditText)findViewById(R.id.Phone);
        editGov = (EditText)findViewById(R.id.Gov);
        editAge = (EditText)findViewById(R.id.Age);
        editEmail =  (EditText)findViewById(R.id.Email);

        radioGen = (RadioGroup) findViewById(R.id.radioGen);
        radioDon = (RadioGroup) findViewById(R.id.radioDon);
        radioblood =  (RadioGroup) findViewById(R.id.RadioBlood);


        btnAddData = (Button)findViewById(R.id.Sub);
        btnLogIn = (Button)findViewById(R.id.Log);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            Intent intent = new Intent(Registration.this, LoginPage.class);
                                            startActivity(intent);
                                        }
        });
        AddData();
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId1 = radioGen.getCheckedRadioButtonId();
                        radioButtonGen = (RadioButton) findViewById(selectedId1);

                        int selectedId2 = radioDon.getCheckedRadioButtonId();
                        radioButtonDon = (RadioButton) findViewById(selectedId2);

                        int selectedId3 = radioblood.getCheckedRadioButtonId();
                        radioButtonblood = (RadioButton)findViewById(selectedId3);

                        boolean isInserted = myDb.insertData(editUsername.getText().toString(), editPass.getText().toString(), editName.getText().toString(), editEmail.getText().toString(),editAdd.getText().toString(),editPhone.getText().toString(),editGov.getText().toString(),editAge.getText().toString(),radioButtonGen.getText().toString(),radioButtonDon.getText().toString(),radioButtonblood.getText().toString());

                        if(isInserted == true)
                            Toast.makeText(Registration.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Registration.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
