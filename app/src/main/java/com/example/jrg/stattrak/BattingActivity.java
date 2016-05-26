package com.example.jrg.stattrak;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BattingActivity extends AppCompatActivity {

    /*
    Initializing private views from XML set up
     */

    private EditText singles, doubles, triples, homeruns, atbats, walks, hitbypitch, strikeouts, runs, runsbattedin, stolenbases, caughtstealing;

    private Button Calculate, Pitching, Save, Load, Clear;

    private TextView average, slugging, onbasepercentage, onbaseplusslugging, babip, iso;

//    private Context mContext; //added this
//    private UserManager mManager; //added this

    /*
    Main OnCreate method for starting this instance
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batting);

        /*
        Establishing connection between variables and views from XML
         */

        singles = (EditText) findViewById(R.id.editsingles);
        doubles = (EditText) findViewById(R.id.editdoubles);
        triples = (EditText) findViewById(R.id.edittriples);
        homeruns = (EditText) findViewById(R.id.edithomeruns);
        atbats = (EditText) findViewById(R.id.editatbats);
        walks = (EditText) findViewById(R.id.editwalks);
        hitbypitch = (EditText) findViewById(R.id.edithitbypitch);
        strikeouts = (EditText) findViewById(R.id.editstrikeouts);
        runs = (EditText) findViewById(R.id.editruns);
        runsbattedin = (EditText) findViewById(R.id.editrbis);
        stolenbases = (EditText) findViewById(R.id.editsteals);
        caughtstealing = (EditText) findViewById(R.id.editcaughtstealing);

        Calculate = (Button) findViewById(R.id.button);
        Pitching = (Button) findViewById(R.id.button2);
        Save = (Button) findViewById(R.id.button5);
        Load = (Button) findViewById(R.id.button6);
        Clear = (Button) findViewById(R.id.button7);


        average = (TextView) findViewById(R.id.average);
        slugging = (TextView) findViewById(R.id.slugging);
        onbasepercentage = (TextView) findViewById(R.id.onbasepercentage);
        onbaseplusslugging = (TextView) findViewById(R.id.onbaseplusslugging);
        babip = (TextView) findViewById(R.id.babip);
        iso = (TextView) findViewById(R.id.ISO);

        /*
        Starting SharedPreferences to store data for "batting" page
         */

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref_Batting", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        /*
        Calculate method for hitting
         */

        Calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ((singles.getText().length() > 0) && (doubles.getText().length() > 0) && (triples.getText().length() > 0) && (homeruns.getText().length() > 0) && (atbats.getText().length() > 0) && (walks.getText().length() > 0)
                        && (hitbypitch.getText().length() > 0) && (strikeouts.getText().length() > 0) && (runs.getText().length() > 0) && (runsbattedin.getText().length() > 0) && (stolenbases.getText().length() > 0)
                        && (caughtstealing.getText().length() > 0)) {

                    double sgl = Double.parseDouble(singles.getText().toString());
                    double dbl = Double.parseDouble(doubles.getText().toString());
                    double trp = Double.parseDouble(triples.getText().toString());
                    double hrs = Double.parseDouble(homeruns.getText().toString());
                    double atb = Double.parseDouble(atbats.getText().toString());
                    double bbs = Double.parseDouble(walks.getText().toString());
                    double hbp = Double.parseDouble(hitbypitch.getText().toString());
                    double sos = Double.parseDouble(strikeouts.getText().toString());
//                    double run = Double.parseDouble(runs.getText().toString());
//                    double rbi = Double.parseDouble(runsbattedin.getText().toString());
//                    double sbs = Double.parseDouble(stolenbases.getText().toString());
//                    double css = Double.parseDouble(caughtstealing.getText().toString());


                    double avg = (sgl + dbl + trp + hrs) / (atb);
                    double obp = (sgl + dbl + trp + hrs + bbs + hbp) / (atb + bbs + hbp);
                    double slg = (sgl + 2 * dbl + 3 * trp + 4 * hrs) / (atb);
                    double ops = ((sgl + dbl + trp + hrs + bbs + hbp) / (atb + bbs + hbp) + (sgl + 2 * dbl + 3 * trp + 4 * hrs) / (atb));
                    double bab = (sgl + dbl + trp - hrs) / (atb - sos - hrs);
                    double ISO = (dbl + 2 * trp + 3 * hrs) / (atb);

                    average.setText(String.format("%.3f", avg));
                    onbasepercentage.setText(String.format("%.3f", obp));
                    slugging.setText(String.format("%.3f", slg));
                    onbaseplusslugging.setText(String.format("%.3f", ops));
                    babip.setText(String.format("%.3f", bab));
                    iso.setText(String.format("%.3f", ISO));


                } else {
                    Toast.makeText(BattingActivity.this, "Please fill out all required stats, with 0's if no stats collected.", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        Pitching method for intent switch to pitching page
         */

        Pitching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PitchingActivity.class);
                startActivity(intent);
            }
        });

        /*
        Save method to save data as integers for future recall
         */

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((singles.getText().length() > 0) && (doubles.getText().length() > 0) && (triples.getText().length() > 0) && (homeruns.getText().length() > 0) && (atbats.getText().length() > 0) && (walks.getText().length() > 0)
                        && (hitbypitch.getText().length() > 0) && (strikeouts.getText().length() > 0) && (runs.getText().length() > 0) && (runsbattedin.getText().length() > 0) && (stolenbases.getText().length() > 0)
                        && (caughtstealing.getText().length() > 0)) {

                    int sgl = Integer.parseInt(singles.getText().toString());
                    int dbl = Integer.parseInt(doubles.getText().toString());
                    int trp = Integer.parseInt(triples.getText().toString());
                    int hrs = Integer.parseInt(homeruns.getText().toString());
                    int atb = Integer.parseInt(atbats.getText().toString());
                    int bbs = Integer.parseInt(walks.getText().toString());
                    int hbp = Integer.parseInt(hitbypitch.getText().toString());
                    int sos = Integer.parseInt(strikeouts.getText().toString());
                    int run = Integer.parseInt(runs.getText().toString());
                    int rbi = Integer.parseInt(runsbattedin.getText().toString());
                    int sbs = Integer.parseInt(stolenbases.getText().toString());
                    int css = Integer.parseInt(caughtstealing.getText().toString());
                    editor.putInt("singles", sgl);
                    editor.putInt("doubles", dbl);
                    editor.putInt("triples", trp);
                    editor.putInt("homeruns", hrs);
                    editor.putInt("atbats", atb);
                    editor.putInt("walks", bbs);
                    editor.putInt("hitbypitch", hbp);
                    editor.putInt("strikeouts", sos);
                    editor.putInt("runs", run);
                    editor.putInt("runsbattedin", rbi);
                    editor.putInt("stolenbases", sbs);
                    editor.putInt("caughtstealing", css);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "User Data Saved!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(BattingActivity.this, "Please fill out all required stats, with 0's if no stats collected.", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        Load method to load saved data from SharedPreferences
         */

        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sgl = pref.getInt("singles", 0);
                int dbl = pref.getInt("doubles", 0);
                int trp = pref.getInt("triples", 0);
                int hrs = pref.getInt("homeruns", 0);
                int atb = pref.getInt("atbats", 0);
                int bbs = pref.getInt("walks", 0);
                int hbp = pref.getInt("hitbypitch", 0);
                int sos = pref.getInt("strikeouts", 0);
                int run = pref.getInt("runs", 0);
                int rbi = pref.getInt("runsbattedin", 0);
                int sbs = pref.getInt("stolenbases", 0);
                int css = pref.getInt("caughtstealing", 0);
                singles.setText(String.valueOf(sgl));
                doubles.setText(String.valueOf(dbl));
                triples.setText(String.valueOf(trp));
                homeruns.setText(String.valueOf(hrs));
                atbats.setText(String.valueOf(atb));
                walks.setText(String.valueOf(bbs));
                hitbypitch.setText(String.valueOf(hbp));
                strikeouts.setText(String.valueOf(sos));
                runs.setText(String.valueOf(run));
                runsbattedin.setText(String.valueOf(rbi));
                stolenbases.setText(String.valueOf(sbs));
                caughtstealing.setText(String.valueOf(css));
                Toast.makeText(getApplicationContext(), "User Data Loaded!", Toast.LENGTH_LONG).show();

            }
        });

        /*
        Clear method to clear data stored in baseball
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
    Settings menu created
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        mManager = new UserManager(this); //added this
//        mContext = this; //added this

        int id = item.getItemId();

        switch (id) {


            case R.id.menu_save:

//                if ((singles.getText().length() > 0) && (doubles.getText().length() > 0) && (triples.getText().length() > 0) && (homeruns.getText().length() > 0) && (atbats.getText().length() > 0) && (walks.getText().length() > 0)
//                        && (hitbypitch.getText().length() > 0) && (strikeouts.getText().length() > 0) && (runs.getText().length() > 0) && (runsbattedin.getText().length() > 0) && (stolenbases.getText().length() > 0)
//                        && (caughtstealing.getText().length() > 0)) {
//
//                    int sgl = Integer.parseInt(singles.getText().toString());
//                    int dbl = Integer.parseInt(doubles.getText().toString());
//                    int trp = Integer.parseInt(triples.getText().toString());
//                    int hrs = Integer.parseInt(homeruns.getText().toString());
//                    int atb = Integer.parseInt(atbats.getText().toString());
//                    int bbs = Integer.parseInt(walks.getText().toString());
//                    int hbp = Integer.parseInt(hitbypitch.getText().toString());
//                    int sos = Integer.parseInt(strikeouts.getText().toString());
//                    int run = Integer.parseInt(runs.getText().toString());
//                    int rbi = Integer.parseInt(runsbattedin.getText().toString());
//                    int sbs = Integer.parseInt(stolenbases.getText().toString());
//                    int css = Integer.parseInt(caughtstealing.getText().toString());
//                    mManager.saveUserData(sgl);
//                    mManager.saveUserData(dbl);
//                    mManager.saveUserData(trp);
//                    mManager.saveUserData(hrs);
//                    mManager.saveUserData(atb);
//                    mManager.saveUserData(bbs);
//                    mManager.saveUserData(hbp);
//                    mManager.saveUserData(sos);
//                    mManager.saveUserData(run);
//                    mManager.saveUserData(rbi);
//                    mManager.saveUserData(sbs);
//                    mManager.saveUserData(css);

                    Toast.makeText(getApplicationContext(), "User Data Saved!", Toast.LENGTH_LONG).show();

//                } else {
//
//                    Toast.makeText(getApplicationContext(), "Please fill out all required stats, with 0's if no stats collected.", Toast.LENGTH_LONG).show();

//                }

                break;

            case R.id.menu_load:



                Toast.makeText(getApplicationContext(), "User Data Loaded!", Toast.LENGTH_LONG).show();

                break;

            case R.id.clear_data:

                Toast.makeText(getApplicationContext(), "User Login Data Cleared!", Toast.LENGTH_LONG).show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}



