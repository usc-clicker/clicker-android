package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.R;
import edu.usc.clicker.model.QuizStatistics;

public class StatisticsListItem extends LinearLayout {
    private TextView quizName;
    private TextView score;

    public void bindQuizStatistics(QuizStatistics quizStatistics) {
        quizName.setText(quizStatistics.getQuizName());
        score.setText(Double.toString(Math.round(quizStatistics.getScore() * 100.0)/100.0) + "%");
    }

    public StatisticsListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        quizName = (TextView) findViewById(R.id.quizName);
        score = (TextView) findViewById(R.id.score);
    }
}
