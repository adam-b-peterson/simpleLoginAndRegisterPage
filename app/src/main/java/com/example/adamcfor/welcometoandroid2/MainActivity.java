package com.example.adamcfor.welcometoandroid2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
        
        //this part is for testing the register page, as once registered account it does not come out
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(MainActivity.USER_NAME, "");
//        editor.putString(MainActivity.USER_PASSWORD, "");
//        editor.apply();

        String userName = sharedPreferences.getString(USER_NAME,"");
        if (userName.equals("")){
            openRegisterDialog();
        }

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String email = etEmail.getText().toString();
                if (!TextUtils.isEmpty(email) && (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                userLogin(v);
            }
        });

        etEmail.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    private void openRegisterDialog() {
        RegisterDialog registerDialog = new RegisterDialog();
        registerDialog.show(getSupportFragmentManager(), "registerDialog");
    }

    public void userLogin(View view) {
        String usernameData = sharedPreferences.getString(USER_NAME,"");
        String usernameInput = etEmail.getText().toString();
        String passwordData = sharedPreferences.getString(USER_PASSWORD,"");
        String passwordInput = etPassword.getText().toString();
        if (usernameData.equals("") || passwordData.equals("")){
            openRegisterDialog();
            etEmail.setText("");
            etPassword.setText("");
            return;
        }
        if (usernameData.equals(usernameInput) && passwordData.equals(passwordInput)){
            Toast.makeText(this, "Login Sucessful",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MemberActivity.class);
            startActivity(i);
        } else {
            etPassword.setText("");
            Toast.makeText(this,"User Name or Password is wrong. Please try again.",Toast.LENGTH_LONG).show();
        }
    }

}
