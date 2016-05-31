package com.example.jrg.stattrak;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayStatsActivity extends AppCompatActivity {

    private StatisticsDbAdapter mydb;

    EditText title, singles, doubles, triples, homeruns, atbats, walks, hitbypitch, strikeouts, runs, runsbattedin, stolenbases, caughtstealing;
    int id_To_Update = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_statistics);

        title = (EditText) findViewById(R.id.editTitle);
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

        mydb = new StatisticsDbAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
//                rs.moveToFirst();

                if (rs != null && rs.moveToFirst()) {

                    String ctitle = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_TITLE));
                    String csingles = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_SINGLES));
                    String cdoubles = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_DOUBLES));
                    String ctriples = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_TRIPLES));
                    String chomeruns = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_HOMERUNS));
                    String catbats = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_ATBATS));
                    String cwalks = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_WALKS));
                    String chitbypitch = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_HITBYPITCH));
                    String cstrikeouts = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_STRIKEOUTS));
                    String cruns = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_RUNS));
                    String crbis = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_RUNSBATTEDIN));
                    String cstolenbases = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_STOLENBASES));
                    String ccaughtstealing = rs.getString(rs.getColumnIndex(StatisticsDbAdapter.COLUMN_CAUGHTSTEALING));


                    if (!rs.isClosed()) {
                        rs.close();
                    }

                    Button b = (Button) findViewById(R.id.button5);
                    b.setVisibility(View.INVISIBLE);

                    title.setText((CharSequence) ctitle);
                    title.setFocusable(false);
                    title.setClickable(false);

                    singles.setText((CharSequence) csingles);
                    singles.setFocusable(false);
                    singles.setClickable(false);

                    doubles.setText((CharSequence) cdoubles);
                    doubles.setFocusable(false);
                    doubles.setClickable(false);

                    triples.setText((CharSequence) ctriples);
                    triples.setFocusable(false);
                    triples.setClickable(false);

                    homeruns.setText((CharSequence) chomeruns);
                    homeruns.setFocusable(false);
                    homeruns.setClickable(false);

                    atbats.setText((CharSequence) catbats);
                    atbats.setFocusable(false);
                    atbats.setClickable(false);

                    walks.setText((CharSequence) cwalks);
                    walks.setFocusable(false);
                    walks.setClickable(false);

                    hitbypitch.setText((CharSequence) chitbypitch);
                    hitbypitch.setFocusable(false);
                    hitbypitch.setClickable(false);

                    strikeouts.setText((CharSequence) cstrikeouts);
                    strikeouts.setFocusable(false);
                    strikeouts.setClickable(false);

                    runs.setText((CharSequence) cruns);
                    runs.setFocusable(false);
                    runs.setClickable(false);

                    runsbattedin.setText((CharSequence) crbis);
                    runsbattedin.setFocusable(false);
                    runsbattedin.setClickable(false);

                    stolenbases.setText((CharSequence) cstolenbases);
                    stolenbases.setFocusable(false);
                    stolenbases.setClickable(false);

                    caughtstealing.setText((CharSequence) ccaughtstealing);
                    caughtstealing.setFocusable(false);
                    caughtstealing.setClickable(false);
                }
            }
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.display_statistic, menu);
            } else {
                getMenuInflater().inflate(R.menu.main_menu, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:
                Button b = (Button) findViewById(R.id.button5);
                b.setVisibility(View.VISIBLE);
                title.setEnabled(true);
                title.setFocusableInTouchMode(true);
                title.setClickable(true);

                singles.setEnabled(true);
                singles.setFocusableInTouchMode(true);
                singles.setClickable(true);

                doubles.setEnabled(true);
                doubles.setFocusableInTouchMode(true);
                doubles.setClickable(true);

                triples.setEnabled(true);
                triples.setFocusableInTouchMode(true);
                triples.setClickable(true);

                homeruns.setEnabled(true);
                homeruns.setFocusableInTouchMode(true);
                homeruns.setClickable(true);

                atbats.setEnabled(true);
                atbats.setFocusableInTouchMode(true);
                atbats.setClickable(true);

                walks.setEnabled(true);
                walks.setFocusableInTouchMode(true);
                walks.setClickable(true);

                hitbypitch.setEnabled(true);
                hitbypitch.setFocusableInTouchMode(true);
                hitbypitch.setClickable(true);

                strikeouts.setEnabled(true);
                strikeouts.setFocusableInTouchMode(true);
                strikeouts.setClickable(true);

                runs.setEnabled(true);
                runs.setFocusableInTouchMode(true);
                runs.setClickable(true);

                runsbattedin.setEnabled(true);
                runsbattedin.setFocusableInTouchMode(true);
                runsbattedin.setClickable(true);

                stolenbases.setEnabled(true);
                stolenbases.setFocusableInTouchMode(true);
                stolenbases.setClickable(true);

                caughtstealing.setEnabled(true);
                caughtstealing.setFocusableInTouchMode(true);
                caughtstealing.setClickable(true);

                return true;
            case R.id.Delete_Contact:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteContact)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteStat(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure?");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void run(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateStats(id_To_Update,title.getText().toString(), singles.getText().toString(),
                        doubles.getText().toString(), triples.getText().toString(),
                        homeruns.getText().toString(), atbats.getText().toString(),
                        walks.getText().toString(), hitbypitch.getText().toString(),
                        strikeouts.getText().toString(), runs.getText().toString(),
                        runsbattedin.getText().toString(), stolenbases.getText().toString(),
                        caughtstealing.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(mydb.insertStat(title.getText().toString(), singles.getText().toString(),
                        doubles.getText().toString(), triples.getText().toString(),
                        homeruns.getText().toString(), atbats.getText().toString(),
                        walks.getText().toString(), hitbypitch.getText().toString(),
                        strikeouts.getText().toString(), runs.getText().toString(),
                        runsbattedin.getText().toString(), stolenbases.getText().toString(),
                        caughtstealing.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        }
    }

}
