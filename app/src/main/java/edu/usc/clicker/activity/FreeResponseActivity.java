package edu.usc.clicker.activity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.R;
import edu.usc.clicker.model.FreeResponseQuestion;
import edu.usc.clicker.util.Timer;
import edu.usc.clicker.view.TimerView;

public class FreeResponseActivity extends ResponseActivity implements Timer.TimerListener, View.OnClickListener {

    EditText answer;
    TimerView timeRemaining;
    LinearLayout content;
    LinearLayout root;
    AppCompatButton submit;
    TextView questionText;
    FreeResponseQuestion question;
    Vibrator vibrator;

    public static void start(Context context) {
        Intent intent = new Intent(context, FreeResponseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void start(Context context, FreeResponseQuestion question) {
        context.startActivity(getIntent(context, question));
    }

    public static Intent getIntent(Context context, FreeResponseQuestion question) {
        Intent intent = new Intent(context, FreeResponseActivity.class);
        intent.putExtra("question", question);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_response);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        question = getIntent().getParcelableExtra("question");

        answer = (EditText) findViewById(R.id.response);
        timeRemaining = (TimerView) findViewById(R.id.timeRemaining);
        content = (LinearLayout) findViewById(R.id.content);
        root = (LinearLayout) findViewById(R.id.root);
        submit = (AppCompatButton) findViewById(R.id.submit);
        questionText = (TextView) findViewById(R.id.question);

        submit.setOnClickListener(this);

        setQuestion(question);
    }

    private void setQuestion(FreeResponseQuestion question) {
        answer.requestFocus();

        answer.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(answer, 0);
            }
        }, 300);

        content.setAlpha(0.0f);
        content.setTranslationY(getResources().getDisplayMetrics().heightPixels);

        questionText.setText(question.getQuestion());

        content.animate().alpha(1.0f).translationY(0.0f).setInterpolator(new DecelerateInterpolator()).setDuration(700).start();

        timeRemaining.setListener(this);
        timeRemaining.start((int) (question.getTimeLimit()/1000L));

        vibrator.vibrate(1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_free_response, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void rippleTimeRemaining() {
        int color1 = getResources().getColor(R.color.colorPrimary);
        int color2 = getResources().getColor(R.color.colorWarning);

        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), color1, color2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                root.setBackgroundDrawable(new ColorDrawable((Integer) animation.getAnimatedValue()));
            }
        });

        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        final ValueAnimator animator2 = ValueAnimator.ofObject(new ArgbEvaluator(), color2, color1);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                root.setBackgroundDrawable(new ColorDrawable((Integer) animation.getAnimatedValue()));
            }
        });

        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animator2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();
    }

    @Override
    public void onTick(int seconds) {
        if (seconds <= 10) {
            rippleTimeRemaining();
        }
    }

    @Override
    public void onFinish() {
        finish();
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(root, "Answer submitted. You can still change your answer.", Snackbar.LENGTH_SHORT).show();
    }
}
