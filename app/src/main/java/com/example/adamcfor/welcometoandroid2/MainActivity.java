package com.example.adamcfor.welcometoandroid2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    Constants
    public final static String USER_NAME = "USER_NAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_DATA = "USER_DATA";

//    Variables
    private EditText etEmail;
    private EditText etPassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        sharedPreferences = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
//        if (sharedPreferences.getString(USER_NAME, "") == null) {
//            openRegisterDialog();
//        }
    }

    private void openRegisterDialog() {
        RegisterDialog registerDialog = new RegisterDialog();
        registerDialog.show(getSupportFragmentManager(), "registerDialog");

    }

    public void userLogin(View view) {
    }


    public void userRegister(View view) {
        openRegisterDialog();
    }

    public void userRegisterCheck(View view) {

    }
}
