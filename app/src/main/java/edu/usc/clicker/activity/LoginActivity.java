package edu.usc.clicker.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.squareup.okhttp.ResponseBody;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.LoginBody;
import edu.usc.clicker.util.ClickerLog;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends Activity implements View.OnClickListener, Callback<ResponseBody> {

    EditText email;
    EditText password;
    AppCompatButton login;
    FrameLayout loadingLayout;

    LoginBody loginBody;

    @Override
    public void onBackPressed() {
        WelcomeActivity.start(this);
        finish();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (email.getText().toString().isEmpty()) {
            email.setError("Please enter a valid email address.");
        } else if (!email.getText().toString().contains("@usc.edu")) {
            email.setError("A usc.edu email address is required.");
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Please enter a password.");
        } else {
            ClickerLog.d("LoginActivity", "Logging in...");
            showLoadingLayout();
            loginBody = new LoginBody(email.getText().toString(), password.getText().toString());
            ClickerApplication.CLICKER_API.login(loginBody).enqueue(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (AppCompatButton) findViewById(R.id.login);
        loadingLayout = (FrameLayout) findViewById(R.id.loadingLayout);

        login.setOnClickListener(this);
    }

    @Override
    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
        if (response.code() == 200) {
            ClickerApplication.LOGIN_HELPER.login(email.getText().toString(), password.getText().toString(), this);
            MainActivity.start(this);
            finish();
        } else {
            hideLoadingLayout();
            Snackbar.make(findViewById(R.id.root), R.string.bad_login, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        hideLoadingLayout();
        Snackbar.make(findViewById(R.id.root), R.string.login_error, Snackbar.LENGTH_LONG).show();
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
