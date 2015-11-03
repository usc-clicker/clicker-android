package edu.usc.clicker.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.MultipleChoiceQuestion;

public class MainActivity extends AppCompatActivity {

    LinearLayout content;
    CardView currentClassCard;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!ClickerApplication.LOGIN_HELPER.isLoggedIn(this)) {
            WelcomeActivity.start(this);
            finish();
        }

        ClickerApplication.connect(this);

        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        content = (LinearLayout) findViewById(R.id.content);
        currentClassCard = (CardView) findViewById(R.id.currentClassCard);

        content.setAlpha(0.0f);
        content.setTranslationY(getResources().getDisplayMetrics().heightPixels);
        currentClassCard.setTranslationY(getResources().getDisplayMetrics().heightPixels);

        content.animate()
                .alpha(1.0f)
                .translationY(0.0f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(1000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        currentClassCard.animate().translationY(0.0f).setInterpolator(new DecelerateInterpolator()).setDuration(300).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
        currentClassCard.animate().alpha(1.0f).translationY(0.0f).setInterpolator(new DecelerateInterpolator()).setDuration(1000).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.logout) {
            ClickerApplication.LOGIN_HELPER.logout(this);
            WelcomeActivity.start(this);
            finish();
        } else if (item.getItemId() == R.id.my_classes) {
            MyClassesActivity.start(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
