package edu.usc.clicker.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.InputType;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.squareup.okhttp.ResponseBody;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.LoginBody;
import edu.usc.clicker.model.RegisterBody;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CreateAccountActivity extends AppCompatActivity implements Callback<ResponseBody> {

    private EditText email;
    private EditText studentID;
    private EditText password;
    private EditText confirmPassword;
    private AppCompatButton createAccount;
    private AppCompatCheckBox showPassword;
    private FrameLayout loadingLayout;

    private boolean accountCreated = false;

    public static void start(Context context) {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        WelcomeActivity.start(this);
        finish();
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
        loadingLayout = (FrameLayout) findViewById(R.id.loadingLayout);

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

    @Override
    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
        if (response.code() == 200) {
            if (accountCreated) {
                ClickerApplication.getLoginHelper().login(email.getText().toString(), password.getText().toString(), this);
                MainActivity.start(this);
                finish();
            } else {
                accountCreated = true;
                ClickerApplication.CLICKER_API.login(new LoginBody(email.getText().toString(), password.getText().toString())).enqueue(this);
            }
        } else {
            hideLoadingLayout();
            Snackbar.make(findViewById(android.R.id.content), R.string.accountCreationFailed, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

    private void createAccount() {
        showLoadingLayout();
        ClickerApplication.CLICKER_API.register(new RegisterBody(email.getText().toString(), password.getText().toString(), Long.parseLong(studentID.getText().toString()))).enqueue(this);
    }

    private void showLoadingLayout() {
        loadingLayout.animate()
                .alpha(1.0f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        loadingLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    private void hideLoadingLayout() {
        loadingLayout.animate()
                .alpha(0.0f)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(300)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadingLayout.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }
}
