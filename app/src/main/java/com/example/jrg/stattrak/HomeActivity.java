package com.example.jrg.stattrak;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    StatisticsDbAdapter mydb;
    public HashMap<String, Integer> hashMap = new HashMap<>(); //this is the new Hashmap


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mydb = new StatisticsDbAdapter(this);
        ArrayList array_list = mydb.getAllStats();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);

        obj = (ListView) findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        /*
         New code starts here
        */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part
                Cursor cursor = mydb.getData(Value);
                hashMap.put(cursor.getString(1), cursor.getInt(0));


                obj.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                int id_To_Search = arg2 + 1;
//
//                Bundle dataBundle = new Bundle();
//                dataBundle.putInt("id", id_To_Search);
//
//                Intent intent = new Intent(getApplicationContext(), DisplayStatsActivity.class);
//
//                intent.putExtras(dataBundle);
//                startActivity(intent);
                        TextView pos = (TextView) findViewById(R.id.editTitle);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putInt("id", hashMap.get(pos.getText().toString()));

                        Intent intent = new Intent(getApplicationContext(), DisplayStatsActivity.class);

                        intent.putExtras(dataBundle);
                        startActivity(intent);
                        //                displayText(Integer.parseInt(pos.getText().toString()));
                    }
                });
            }
        }
    }


    /*
    Added "displayText" method, fix this, it's not working
     */

//    public void displayText(int pos)
//    {
//        pos++;
//        String s = "";
//        s += pos;
//
//        Intent intent = new Intent(getApplicationContext(), DisplayStatsActivity.class);
//        intent.putExtra("id", s);
//        startActivity(intent);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(), DisplayStatsActivity.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            case R.id.clear_data:
                mydb.removeAll();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

}