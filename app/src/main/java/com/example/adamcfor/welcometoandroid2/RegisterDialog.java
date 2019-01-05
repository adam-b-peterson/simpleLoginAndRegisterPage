package com.example.adamcfor.welcometoandroid2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterDialog extends AppCompatDialogFragment {
    private EditText etRegisterEmail;
    private EditText etRegisterPassword;
    private EditText etRegisterPasswordReenter;
    private Button btnUserRegisterCheck;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view).setTitle("Register");

        etRegisterEmail = (EditText) view.findViewById(R.id.etRegisterEmail);
        etRegisterPassword = (EditText) view.findViewById(R.id.etRegisterPassword);
        etRegisterPasswordReenter = (EditText) view.findViewById(R.id.etRegisterPasswordReenter);

        etRegisterEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String email = etRegisterEmail.getText().toString();
                if (!TextUtils.isEmpty(email) && (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                    btnUserRegisterCheck.setEnabled(true);
                } else {
                    btnUserRegisterCheck.setEnabled(false);
                }
            }
        });

        btnUserRegisterCheck = view.findViewById(R.id.btUserRegisterCheck);
        btnUserRegisterCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveInfo(view);
            }
        });

        etRegisterEmail.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etRegisterPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etRegisterPasswordReenter.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        return builder.create();
    }

    private void saveInfo(View view) {
        String pw1 = etRegisterPassword.getText().toString();
        String pw2 = etRegisterPasswordReenter.getText().toString();
        if (pw1.equals(pw2)) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(MainActivity.USER_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MainActivity.USER_NAME, etRegisterEmail.getText().toString());
            editor.putString(MainActivity.USER_PASSWORD, etRegisterPassword.getText().toString());
            editor.apply();
            Toast.makeText(this.getContext(), "Registered", Toast.LENGTH_LONG).show();
            this.dismiss();
        } else {

            etRegisterPassword.setHint("Two passwords are not match");
            etRegisterPasswordReenter.setHint("Two passwords are not match");

            etRegisterPassword.setText("");
            etRegisterPasswordReenter.setText("");

        }

    }


}
