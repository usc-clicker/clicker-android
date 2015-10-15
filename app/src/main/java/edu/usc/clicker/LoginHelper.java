package edu.usc.clicker;

import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.okhttp.ResponseBody;

import edu.usc.clicker.model.LoginBody;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginHelper {
    private static final String EMAIL = "auth_email";
    private static final String PASSWORD = "auth_password";

    public static boolean isLoggedIn(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPreferences.contains(EMAIL) && sharedPreferences.contains(PASSWORD);
    }

    public static void login(String email, String password, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);

        editor.apply();
    }

    public static void logout(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(EMAIL);
        editor.remove(PASSWORD);

        editor.apply();
    }
}
