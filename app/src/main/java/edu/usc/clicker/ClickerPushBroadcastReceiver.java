package edu.usc.clicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import edu.usc.clicker.activity.FreeResponseActivity;
import edu.usc.clicker.activity.MultipleChoiceActivity;
import edu.usc.clicker.activity.NumericResponseActivity;
import edu.usc.clicker.model.FreeResponseQuestion;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.model.NumericResponseQuestion;

public class ClickerPushBroadcastReceiver extends ParsePushBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(ClickerApplication.DISCONNECT_ACTION)) {
            ClickerApplication.disconnect();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.cancel(ClickerApplication.DISCONNECT_ID);
        }
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            Log.d("Clicker", json.toString());

            String type = json.getString("type");

            if (ClickerApplication.getShouldAutoLaunch()) {
                if (type.equals("multiple-choice")) {
                    MultipleChoiceQuestion question = ClickerApplication.GSON.fromJson(json.toString(), MultipleChoiceQuestion.class);
                    MultipleChoiceActivity.start(context, question);
                } else if (type.equals("free-response")) {
                    FreeResponseQuestion question = ClickerApplication.GSON.fromJson(json.toString(), FreeResponseQuestion.class);
                    FreeResponseActivity.start(context, question);
                } else if (type.equals("numeric")) {
                    NumericResponseQuestion question = ClickerApplication.GSON.fromJson(json.toString(), NumericResponseQuestion.class);
                    NumericResponseActivity.start(context, question);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    @Override
    protected Class<? extends Activity> getActivity(Context context, Intent intent) {
        return super.getActivity(context, intent);
    }
}
