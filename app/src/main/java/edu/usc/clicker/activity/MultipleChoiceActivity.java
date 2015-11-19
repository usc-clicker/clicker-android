package edu.usc.clicker.activity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.MultipleChoiceQuestion;
import edu.usc.clicker.util.Timer;
import edu.usc.clicker.view.MultipleChoiceList;
import edu.usc.clicker.view.TimerView;

public class MultipleChoiceActivity extends ResponseActivity implements Timer.TimerListener {

    private LinearLayout root;
    private LinearLayout content;
    private MultipleChoiceList multipleChoiceList;
    private TimerView timerView;
    private TextView questionText;
    private Vibrator vibrator;

    public static void start(Context context) {
        Intent intent = new Intent(context, MultipleChoiceActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, MultipleChoiceQuestion question) {
        context.startActivity(getIntent(context, question));
    }

    public static Intent getIntent(Context context, MultipleChoiceQuestion question) {
        Intent intent = new Intent(context, MultipleChoiceActivity.class);
        intent.putExtra("question", question);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        MultipleChoiceQuestion question = getIntent().getParcelableExtra("question");

        root = (LinearLayout) findViewById(R.id.root);
        content = (LinearLayout) findViewById(R.id.content);
        multipleChoiceList = (MultipleChoiceList) findViewById(R.id.multipleChoiceList);
        timerView = (TimerView) findViewById(R.id.timeRemaining);
        questionText = (TextView) findViewById(R.id.question);

        if (question != null) {
            setQuestion(question);
        }

        ClickerApplication.getLocationHelper().setTrackLocation(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClickerApplication.getLocationHelper().setTrackLocation(false);
    }

    private void setQuestion(MultipleChoiceQuestion question) {
        content.setAlpha(0.0f);
        content.setTranslationY(getResources().getDisplayMetrics().heightPixels);

        questionText.setText(question.getQuestion());

        multipleChoiceList.setQuestion(question);

        content.animate().alpha(1.0f).translationY(0.0f).setInterpolator(new DecelerateInterpolator()).setDuration(700).start();

        timerView.setListener(this);

        int maxTime = (int) ((question.getExpiration() - System.currentTimeMillis())/1000L);
        if (maxTime < question.getTimeLimit()/1000L) {
            timerView.start((int) ((question.getExpiration() - System.currentTimeMillis()) / 1000L));
        } else {
            timerView.start((int) (question.getTimeLimit()/1000L));
        }

        vibrator.vibrate(1000);
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
}
