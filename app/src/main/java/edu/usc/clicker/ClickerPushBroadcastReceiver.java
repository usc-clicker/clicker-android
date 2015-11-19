package edu.usc.clicker;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import edu.usc.clicker.activity.FreeResponseActivity;
import edu.usc.clicker.activity.MultipleChoiceActivity;
import edu.usc.clicker.activity.NumericResponseActivity;
import edu.usc.clicker.model.FreeResponseQuestion;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.model.NumericResponseQuestion;
import edu.usc.clicker.util.ClickerLog;

public class ClickerPushBroadcastReceiver extends ParsePushBroadcastReceiver {
    private final int QUIZ_NOTIFICATION = 1000;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(ClickerApplication.DISCONNECT_ACTION)) {
            ClickerApplication.disableAutoLaunch();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.cancel(ClickerApplication.DISCONNECT_ID);
        } else if (intent.getAction().equals("CANCEL")) {
            int id = intent.getIntExtra("notification_id", -1);
            if (id != -1) {
                ClickerLog.d("Clicker", "Dismissing stale quiz notification...");
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(id);
            }
        }
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        if (!ClickerApplication.LOGIN_HELPER.isLoggedIn(context)) {
            return;
        }

        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            ClickerLog.d(json.toString());

            long expiration = json.getLong("expiration");

            if (expiration <= System.currentTimeMillis()/1000L) {
                ClickerLog.d("Received stale quiz, ignoring...");
                return;
            }

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
            } else {
                Intent startIntent = null;
                if (type.equals("multiple-choice")) {
                    MultipleChoiceQuestion question = ClickerApplication.GSON.fromJson(json.toString(), MultipleChoiceQuestion.class);
                    startIntent = MultipleChoiceActivity.getIntent(context, question);
                } else if (type.equals("free-response")) {
                    FreeResponseQuestion question = ClickerApplication.GSON.fromJson(json.toString(), FreeResponseQuestion.class);
                    startIntent = FreeResponseActivity.getIntent(context, question);
                } else if (type.equals("numeric")) {
                    NumericResponseQuestion question = ClickerApplication.GSON.fromJson(json.toString(), NumericResponseQuestion.class);
                    startIntent = NumericResponseActivity.getIntent(context, question);
                }

                if (startIntent != null) {
                    PendingIntent startPendingIntent = PendingIntent.getActivity(context, 0, startIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                    Notification notification = new NotificationCompat.Builder(context.getApplicationContext())
                            .setSmallIcon(R.mipmap.ic_notification_white)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                            .setContentIntent(startPendingIntent)
                            .setContentTitle(context.getString(R.string.quiz_notification_title))
                            .setContentText(context.getString(R.string.quiz_notification_message))
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setOngoing(false)
                            .build();

                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    notification.defaults |= Notification.DEFAULT_ALL;

                    int id = (int) (Math.random() * Integer.MAX_VALUE);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());
                    notificationManager.notify(id, notification);
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
