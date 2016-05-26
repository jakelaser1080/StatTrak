package com.example.jrg.stattrak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PitchingActivity extends AppCompatActivity {

    /*
    Initializing private views from XML set up
     */

    private EditText wins, losses, games, inningspitched, hitsallowed, strikeouts, runsallowed, earnedruns, walksallowed, homerunsallowed;

    private Button calculate, hitting, Save, Load, Clear;

    private TextView earnedrunaverage, walkshitsper, fieldingind;

    /*
    Main onCreate method for starting this instance
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitching);

        /*
        Establishing connection between variables and views from XML
         */

        wins = (EditText) findViewById(R.id.editwins);
        losses = (EditText) findViewById(R.id.editloss);
        games = (EditText) findViewById(R.id.editgames);
        inningspitched = (EditText) findViewById(R.id.editinnings);
        hitsallowed = (EditText) findViewById(R.id.edithits);
        strikeouts = (EditText) findViewById(R.id.editstrikeouts);
        runsallowed = (EditText) findViewById(R.id.editrunsallowed);
        earnedruns = (EditText) findViewById(R.id.editearnedruns);
        walksallowed = (EditText) findViewById(R.id.editwalks);
        homerunsallowed = (EditText) findViewById(R.id.edithomeruns);

        calculate = (Button) findViewById(R.id.button4);
        hitting = (Button) findViewById(R.id.button3);
        Save = (Button) findViewById(R.id.button8);
        Load = (Button) findViewById(R.id.button9);
        Clear = (Button) findViewById(R.id.button10);

        earnedrunaverage = (TextView) findViewById(R.id.editearnedrunaverage);
        walkshitsper = (TextView) findViewById(R.id.whip);
        fieldingind = (TextView) findViewById(R.id.fip);

           /*
        Calculate method for hitting
         */

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref_Pitching", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((wins.getText().length() > 0) && (losses.getText().length() > 0) && (games.getText().length() > 0) && (inningspitched.getText().length() > 0) && (strikeouts.getText().length() > 0) &&
                        (runsallowed.getText().length() > 0) && (earnedruns.getText().length() > 0) && (walksallowed.getText().length() > 0)) {

                    double wns = Double.parseDouble(wins.getText().toString());
                    double lss = Double.parseDouble(losses.getText().toString());
                    double gms = Double.parseDouble(games.getText().toString());
                    double ips = Double.parseDouble(inningspitched.getText().toString());
                    double hts = Double.parseDouble(hitsallowed.getText().toString());
                    double sos = Double.parseDouble(strikeouts.getText().toString());
                    double ras = Double.parseDouble(runsallowed.getText().toString());
                    double ers = Double.parseDouble(earnedruns.getText().toString());
                    double bbs = Double.parseDouble(walksallowed.getText().toString());
                    double hrs = Double.parseDouble(homerunsallowed.getText().toString());

                    double ERA = (9 * ers) / (ips);
                    double whip = (bbs + hts) / (ips);
                    double fip = (hrs * 13 + 3 * bbs - 2 * sos) / (ips) + 3.240;

                    earnedrunaverage.setText(String.format("%.3f", ERA));
                    walkshitsper.setText(String.format("%.3f", whip));
                    fieldingind.setText(String.format("%.3f", fip));


                } else {
                    Toast.makeText(PitchingActivity.this, "Please fill out all required stats, with 0's if no stats collected.", Toast.LENGTH_LONG).show();

                }
            }
        });

        /*
        Hitting method intent for switching to hitting statistics
         */

        hitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BattingActivity.class);
                startActivity(intent);
            }
        });

        /*
        Save method created to save data into sharedpreferences
         */

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((wins.getText().length() > 0) && (losses.getText().length() > 0) && (games.getText().length() > 0) && (inningspitched.getText().length() > 0) && (strikeouts.getText().length() > 0) &&
                        (runsallowed.getText().length() > 0) && (earnedruns.getText().length() > 0) && (walksallowed.getText().length() > 0)) {

                    int wns = Integer.parseInt(wins.getText().toString());
                    int lss = Integer.parseInt(losses.getText().toString());
                    int gms = Integer.parseInt(games.getText().toString());
                    int ips = Integer.parseInt(inningspitched.getText().toString());
                    int hts = Integer.parseInt(hitsallowed.getText().toString());
                    int sos = Integer.parseInt(strikeouts.getText().toString());
                    int ras = Integer.parseInt(runsallowed.getText().toString());
                    int ers = Integer.parseInt(earnedruns.getText().toString());
                    int bbs = Integer.parseInt(walksallowed.getText().toString());
                    int hrs = Integer.parseInt(homerunsallowed.getText().toString());
                    editor.putInt("wins", wns);
                    editor.putInt("losses", lss);
                    editor.putInt("games", gms);
                    editor.putInt("inningspitched", ips);
                    editor.putInt("hitsallowed", hts);
                    editor.putInt("strikeouts", sos);
                    editor.putInt("runsallowed", ras);
                    editor.putInt("earnedruns", ers);
                    editor.putInt("walksallowed", bbs);
                    editor.putInt("homerunsallowed", hrs);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "User Data Saved!", Toast.LENGTH_LONG).show();
                }

            }
        });

        /*
        Load method created to load data from SharedPreferences
         */

        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wns = pref.getInt("wins", 0);
                int lss = pref.getInt("losses", 0);
                int gms = pref.getInt("games", 0);
                int ips = pref.getInt("inningspitched", 0);
                int hts = pref.getInt("hitsallowed", 0);
                int sos = pref.getInt("strikeouts", 0);
                int ras = pref.getInt("runsallowed", 0);
                int ers = pref.getInt("earnedruns", 0);
                int bbs = pref.getInt("walksallowed", 0);
                int hrs = pref.getInt("homerunsallowed", 0);
                wins.setText(String.valueOf(wns));
                losses.setText(String.valueOf(lss));
                games.setText(String.valueOf(gms));
                inningspitched.setText(String.valueOf(ips));
                hitsallowed.setText(String.valueOf(hts));
                strikeouts.setText(String.valueOf(sos));
                runsallowed.setText(String.valueOf(ras));
                earnedruns.setText(String.valueOf(ers));
                walksallowed.setText(String.valueOf(bbs));
                homerunsallowed.setText(String.valueOf(hrs));
                Toast.makeText(getApplicationContext(), "User Data Loaded!", Toast.LENGTH_LONG).show();
            }
        });

        /*
        Clear method created for clearing items stored in pitching shared preferences
         */

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "User Data Cleared!", Toast.LENGTH_LONG).show();
            }
        });

    }

    /*
    Settings menu established here
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.menu_load:

                Toast.makeText(getApplicationContext(), "User Data Loaded!", Toast.LENGTH_LONG).show();

                break;

            case R.id.menu_save:

                Toast.makeText(getApplicationContext(), "User Data Saved!", Toast.LENGTH_LONG).show();

                break;

            case R.id.clear_data:

                Toast.makeText(getApplicationContext(), "User Login Data Cleared!", Toast.LENGTH_LONG).show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
