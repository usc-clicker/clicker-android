package edu.usc.clicker.util;

import android.os.Handler;

public class Timer {
    private int seconds;
    private TimerListener listener;
    private Handler handler;

    public void start() {
        listener.onTick(seconds);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seconds -= 1;
                listener.onTick(seconds);

                if (seconds > 0) {
                    handler.postDelayed(this, 1000);
                } else {
                    listener.onFinish();
                }
            }
        }, 1000);
    }

    public Timer(int seconds, TimerListener listener) {
        this.seconds = seconds;
        this.listener = listener;

        handler = new Handler();
    }

    public interface TimerListener {
        void onTick(int seconds);
        void onFinish();
    }
}
