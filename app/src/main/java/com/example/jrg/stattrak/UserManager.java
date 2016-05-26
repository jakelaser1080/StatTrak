package com.example.jrg.stattrak;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UserManager {

    private final String KEY_PREFS = "prefs_user";
//    private final String KEY_HITS = "prefs_hitting"; //added this


    private final String KEY_USERNAME = "username";


    private final String KEY_PASSWORD = "password";


    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
//    private SharedPreferences mPrefsHitting; //added this
//    private SharedPreferences.Editor mEditorHitting; //added this

    /*
    UserManager method for link to SharedPreferences
     */

    public UserManager(Context context) {
        mPrefs = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
//        mPrefsHitting = context.getSharedPreferences(KEY_HITS, Context.MODE_PRIVATE); //added this
//        mEditorHitting = mPrefsHitting.edit(); //added this

    }

    /*
    checks the login for validation
     */

    public boolean checkLoginValidate(String username, String password) {
        String realUsername = mPrefs.getString(KEY_USERNAME, "");
        String realPassword = mPrefs.getString(KEY_PASSWORD, "");

        if ((!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) &&
                username.equals(realUsername) &&
                password.equals(realPassword)) {
            return true;
        }
        return false;
    }

    /*
    registers the user if accurate information is included
     */

    public boolean registerUser(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }

        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_PASSWORD, password);
        return mEditor.commit();
    }

    /*
    clears the user log in information for new user
     */

    public void clearUserData() {

        mEditor.clear();
        mEditor.commit();
    }

//    public void saveUserData(int statistic) {
//
//        mEditorHitting.putInt(KEY_HITS, statistic);
//        mEditorHitting.commit();
//
//    }
//
//    public void loadUserData () {
//
//        mPrefsHitting.getInt(KEY_HITS, 0);
//
//
//    }
}
