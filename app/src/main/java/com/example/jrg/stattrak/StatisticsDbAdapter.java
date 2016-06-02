package com.example.jrg.stattrak;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;
import android.util.Log;

import java.util.ArrayList;

public class StatisticsDbAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "statistics.db";
    private static final int DATABASE_VERSION = 1;

    //every one of these columns represents the information about the data
    public static final String STATS_TABLE = "stats";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SINGLES = "singles";
    public static final String COLUMN_DOUBLES = "doubles";
    public static final String COLUMN_TRIPLES = "triples";
    public static final String COLUMN_HOMERUNS = "homeruns";
    public static final String COLUMN_ATBATS = "atbats";
    public static final String COLUMN_WALKS = "walks";
    public static final String COLUMN_HITBYPITCH = "hitbypitch";
    public static final String COLUMN_STRIKEOUTS = "strikeouts";
    public static final String COLUMN_RUNS = "runs";
    public static final String COLUMN_RUNSBATTEDIN = "runsbattedin";
    public static final String COLUMN_STOLENBASES = "stolenbases";
    public static final String COLUMN_CAUGHTSTEALING = "caughtstealing";

    public StatisticsDbAdapter(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /*

    private String[] allColumns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGLES, COLUMN_DOUBLES,
            COLUMN_TRIPLES, COLUMN_HOMERUNS, COLUMN_ATBATS, COLUMN_WALKS, COLUMN_HITBYPITCH,
            COLUMN_STRIKEOUTS, COLUMN_RUNS, COLUMN_RUNSBATTEDIN, COLUMN_STOLENBASES,
            COLUMN_CAUGHTSTEALING, COLUMN_DATE};

    public static final String CREATE_TABLE_STATS = "create table " + STATS_TABLE +
            " ( " + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_TITLE +
            " text not null " + COLUMN_SINGLES + " text not null " + COLUMN_DOUBLES +
            " text not null " + COLUMN_TRIPLES + " text not null " + COLUMN_HOMERUNS +
            " text not null " + COLUMN_ATBATS + " text not null " + COLUMN_WALKS +
            " text not null " + COLUMN_HITBYPITCH + " text not null " + COLUMN_STRIKEOUTS +
            " text not null " + COLUMN_RUNS + " text not null " + COLUMN_RUNSBATTEDIN +
            " text not null " + COLUMN_STOLENBASES + " text not null " + COLUMN_CAUGHTSTEALING +
            " text not null " + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    */

    //removed TITLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table stats " + "(id integer primary key autoincrement, title text, singles text," +
                        " doubles text, triples text, homeruns text, atbats text, walks text," +
                        " hitbypitch text, strikeouts text, runs text, runsbattedin text," +
                        " stolenbases text, caughtstealing text)"
//                "create table " + STATS_TABLE +
//                        " ( " + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_SINGLES + " text not null, " + COLUMN_DOUBLES +
//                        " text not null, " + COLUMN_TRIPLES + " text not null, " + COLUMN_HOMERUNS +
//                        " text not null, " + COLUMN_ATBATS + " text not null, " + COLUMN_WALKS +
//                        " text not null, " + COLUMN_HITBYPITCH + " text not null, " +
//                        COLUMN_STRIKEOUTS + " text not null, " + COLUMN_RUNS + " text not null, " +
//                        COLUMN_RUNSBATTEDIN + " text not null, " + COLUMN_STOLENBASES +
//                        " text not null, " + COLUMN_CAUGHTSTEALING + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STATS_TABLE);
        onCreate(db);
    }

    //removed String title
    public boolean insertStat(String title, String singles, String doubles, String triples,
                              String homeruns, String atbats, String walks, String hitbypitch,
                              String strikeouts, String runs, String runsbattedin,
                              String stolenbases, String caughtstealing) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("singles", singles);
        contentValues.put("doubles", doubles);
        contentValues.put("triples", triples);
        contentValues.put("homeruns", homeruns);
        contentValues.put("atbats", atbats);
        contentValues.put("walks", walks);
        contentValues.put("hitbypitch", hitbypitch);
        contentValues.put("strikeouts", strikeouts);
        contentValues.put("runs", runs);
        contentValues.put("runsbattedin", runsbattedin);
        contentValues.put("stolenbases", stolenbases);
        contentValues.put("caughtstealing", caughtstealing);
        db.insert(STATS_TABLE, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM stats where id = " + id, null);
        res.moveToFirst();

//        Cursor res = db.query(STATS_TABLE, COLUMN_ID, null, null, null, null, null);
        return res;

    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, STATS_TABLE);
        return numRows;
    }

    public boolean updateStats(Integer id, String title, String singles, String doubles,
                               String triples, String homeruns, String atbats, String walks,
                               String hitbypitch, String strikeouts, String runs,
                               String runsbattedin, String stolenbases, String caughtstealing) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("singles", singles);
        contentValues.put("doubles", doubles);
        contentValues.put("triples", triples);
        contentValues.put("homeruns", homeruns);
        contentValues.put("atbats", atbats);
        contentValues.put("walks", walks);
        contentValues.put("hitbypitch", hitbypitch);
        contentValues.put("strikeouts", strikeouts);
        contentValues.put("runs", runs);
        contentValues.put("runsbattedin", runsbattedin);
        contentValues.put("stolenbases", stolenbases);
        contentValues.put("caughtstealing", caughtstealing);
        db.update(STATS_TABLE, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }


    public Integer deleteStat(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(STATS_TABLE, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void removeAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STATS_TABLE, null, null);
    }

//    public ArrayList<String> getAllStats() {
//        ArrayList<String> array_list = new ArrayList<String>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from " + STATS_TABLE, null);
//        res.moveToFirst();
//
//        while (res.isAfterLast() == false) {
//            array_list.add(res.getString(res.getColumnIndex(COLUMN_TITLE)));
//            array_list.add(res.getString(res.getColumnIndex(COLUMN_ID)));
//
//            res.moveToNext();
//        }
//        db.close();
//        return array_list;
//    }
    public ArrayList<String> getAllStats() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + STATS_TABLE, null);
        if (res.moveToFirst()) {
            do {
                array_list.add(res.getString(res.getColumnIndex(COLUMN_TITLE)));
            } while (res.moveToNext());
        }

        return array_list;
    }
}

