package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import edu.usc.clicker.util.Timer;

public class TimerView extends TextView implements Timer.TimerListener {
    private Timer.TimerListener listener;
    private Timer timer;

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start(int seconds) {
        timer = new Timer(seconds, this);
        timer.start();
    }

    public void setListener(Timer.TimerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onTick(int seconds) {
        int minutes = seconds/60;
        String time = (minutes == 0 ? "00" : seconds / 60) + ":";
        int remainingSeconds = seconds%60;
        if (remainingSeconds == 00) {
            time += "00";
        } else {
            time += (remainingSeconds < 10 ? "0" + remainingSeconds : remainingSeconds % 60);
        }
        time += " REMAINING";

        setText(time);

        if (listener == null) return;

        listener.onTick(seconds);
    }

    @Override
    public void onFinish() {
        if (listener == null) return;

        listener.onFinish();
    }
}
