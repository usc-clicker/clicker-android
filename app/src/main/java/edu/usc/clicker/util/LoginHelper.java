package edu.usc.clicker.util;

import android.content.Context;
import android.content.SharedPreferences;

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

    public static String getEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        return sharedPreferences.getString(EMAIL, "");
    }
}
