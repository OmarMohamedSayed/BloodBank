package com.example.bloodbank.bloodbank;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_Donor extends AppCompatActivity {
    SQLiteDatabase dbread;
    DatabaseHelper db;
    Cursor cursor;
    ListView listView;
    ListViewAdaptor adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<model> arrayList = new ArrayList<model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__donor);
        db = new DatabaseHelper(this);
        dbread = db.getReadableDatabase();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Users List");

        icon = new int[]{R.drawable.user};
        cursor = dbread.rawQuery("SELECT Name,Donor,KindBlood,Phone FROM " + DatabaseHelper.TABLE_NAME ,null);


        String[] column1 = new String[6];
        String[] column2 = new String[6];
        String[] column3 = new String[6];
        String[] column4 = new String[6];
        int c = 0;
        if (cursor.moveToFirst()){
            do {
                 column1[c] = cursor.getString(0);
                 column2[c] = cursor.getString(1);
                 column3[c] = cursor.getString(2);
                 column4[c] = cursor.getString(3);
                 c++;

            } while(cursor.moveToNext());
        }
        cursor.close();
        dbread.close();


        title = new String[6];
        description = new String[6];
        for (int i =0 ;i < title.length; i++){


            title[i] = column3[i];
            description[i]= "Name :  "+column1[i]+" "+column2[i]+" "+"Phone: "+column4[i];

        }



        listView = findViewById(R.id.listview);

        for (int i =0; i<title.length; i++){
             model model = new model(title[i], description[i], icon[0]);
            //bind all strings in an array
            arrayList.add(model);
            }

        //pass results to listViewAdapter class
        adapter = new ListViewAdaptor(this, arrayList);

        //bind the adapter to the listview
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }



}
