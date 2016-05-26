package com.example.jrg.stattrak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    /*
    Setting up variables to match views in XML
     */

    private Button mLogin;
    private EditText mUsername;
    private EditText mPassword;
    private TextView mRegister;
    private Context mContext;

    /*
    Linking the UserManager activity to Login class
     */

    private UserManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        /*
        Creating a manager and context to control preferences
         */

        mManager = new UserManager(this);

        mContext = this;

        mLogin = (Button) findViewById(R.id.button_login);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mRegister = (TextView) findViewById(R.id.register);

        /*
        Login method created to listen for log in and register requests
         */

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    check log in method
     */

    private void checkLogin() {
        String username = mUsername.getText().toString().trim().toLowerCase();
        String password = mPassword.getText().toString().trim();

        boolean isSuccess = mManager.checkLoginValidate(username, password);

        if (isSuccess) {
            Intent intent = new Intent(mContext, SelectActivity.class);
            startActivity(intent);
            finish();
        } else {
            String message = getString(R.string.login_error_message);
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    /*
    Settings menu options established
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mManager = new UserManager(this);
        mContext = this;

        int id = item.getItemId();

        switch (id) {

            case R.id.menu_load:

                Toast.makeText(getApplicationContext(), "User Data Loaded!", Toast.LENGTH_LONG).show();

                break;

            case R.id.menu_save:

                Toast.makeText(getApplicationContext(), "User Data Saved!", Toast.LENGTH_LONG).show();

                break;

            case R.id.clear_data:

                mManager.clearUserData();
                Toast.makeText(getApplicationContext(), "User Login Data Cleared!", Toast.LENGTH_LONG).show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

}