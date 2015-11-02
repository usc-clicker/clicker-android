package edu.usc.clicker.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.InputType;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import edu.usc.clicker.R;

public class CreateAccountActivity extends AppCompatActivity {

    EditText email;
    EditText studentID;
    EditText password;
    EditText confirmPassword;
    AppCompatButton createAccount;
    AppCompatCheckBox showPassword;

    public static void start(Context context) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);

        email = (EditText) findViewById(R.id.email);
        studentID = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        createAccount = (AppCompatButton) findViewById(R.id.createAccount);
        showPassword = (AppCompatCheckBox) findViewById(R.id.showPassword);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.length() == 0) {
                    Snackbar.make(findViewById(android.R.id.content), R.string.email_invalid, Snackbar.LENGTH_LONG).show();
                } else if (!email.getText().toString().contains("@usc.edu")) {
                    Snackbar.make(findViewById(android.R.id.content), R.string.email_invalid, Snackbar.LENGTH_LONG).show();
                } else if (studentID.length() != 10) {
                    Snackbar.make(findViewById(android.R.id.content), R.string.student_id_invalid, Snackbar.LENGTH_LONG).show();
                } else if (password.length() < 8) {
                    Snackbar.make(findViewById(android.R.id.content), R.string.password_invalid, Snackbar.LENGTH_LONG).show();
                } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), R.string.passwords_dont_match, Snackbar.LENGTH_LONG).show();
                } else {
                    createAccount();
                }
            }
        });

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    confirmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    private void createAccount() {
        //do account creation here

    }
}
