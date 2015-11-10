package edu.usc.clicker;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.Arrays;

import edu.usc.clicker.activity.MainActivity;
import edu.usc.clicker.api.ClickerAPI;
import edu.usc.clicker.util.LocationHelper;
import edu.usc.clicker.util.LoginHelper;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ClickerApplication extends Application {

    public static Gson GSON = new Gson();
    public static final int DISCONNECT_REQUEST_CODE = -1;
    public static final int DISCONNECT_ID = -1;
    public static final String DISCONNECT_ACTION = "edu.usc.clicker.ClickerApplication.DISCONNECT_ACTION";
    public static Intent disconnectIntent;
    public static boolean shouldAutoLaunch = true;
    public static LocationHelper LOCATION_HELPER;

    public static OkHttpClient OK_CLIENT = createOkHttpClient();

    public static Retrofit RETROFIT = new Retrofit.Builder()
            .client(OK_CLIENT)
            .baseUrl("http://fontify.usc.edu")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ClickerAPI CLICKER_API = RETROFIT.create(ClickerAPI.class);

    public static final LoginHelper LOGIN_HELPER = new LoginHelper();

    public static LoginHelper getLoginHelper() {
        return LOGIN_HELPER;
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {
                Log.d("ClickerApplication", s);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);

        return client;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void disconnect() {
        setShouldAutoLaunch(null, false);
    }

    public static void connect(Context context) {
        try {
            Parse.initialize(context.getApplicationContext(), "4dWxGYc9wzZRtcxzL3wXne6gmJiLfKut5AA4H4xL", "e8t0sCOUyo8FFD7RuDUq6GIS4ccJ51GxQX17P15p");
            ParseInstallation.getCurrentInstallation().addAllUnique("channels", Arrays.asList("Students"));
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setShouldAutoLaunch(context, true);
    }

    public static void initLocationHelper(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        LOCATION_HELPER = new LocationHelper(manager);
    }

    public static LocationHelper getLocationHelper() {
        return LOCATION_HELPER;
    }

    public static void setShouldAutoLaunch(Context context, boolean shouldAutoLaunch) {
        ClickerApplication.shouldAutoLaunch = shouldAutoLaunch;

        if (shouldAutoLaunch) {
            disconnectIntent = new Intent(DISCONNECT_ACTION);
            disconnectIntent.putExtra("code", DISCONNECT_REQUEST_CODE);
            disconnectIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent disconnectPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), DISCONNECT_REQUEST_CODE, disconnectIntent, 0);

            Intent startIntent = new Intent(context, MainActivity.class);
            PendingIntent startPendingIntent = PendingIntent.getActivity(context, 0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new NotificationCompat.Builder(context.getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_notification_white)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(startPendingIntent)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(context.getString(R.string.app_running))
                    .setOngoing(true)
                    .addAction(R.drawable.ic_cancel_white_24dp, context.getString(R.string.disconnect), disconnectPendingIntent)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());
            notificationManager.notify(DISCONNECT_REQUEST_CODE, notification);
        }
    }

    public static boolean getShouldAutoLaunch() {
        return ClickerApplication.shouldAutoLaunch;
    }
}
