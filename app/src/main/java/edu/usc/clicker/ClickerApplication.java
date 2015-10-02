package edu.usc.clicker;

import android.app.Application;

import com.google.gson.Gson;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

import java.util.Arrays;

public class ClickerApplication extends Application {

    public static Gson GSON = new Gson();

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "4dWxGYc9wzZRtcxzL3wXne6gmJiLfKut5AA4H4xL", "e8t0sCOUyo8FFD7RuDUq6GIS4ccJ51GxQX17P15p");
        ParseInstallation.getCurrentInstallation().addAllUnique("channels", Arrays.asList("Students"));
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
