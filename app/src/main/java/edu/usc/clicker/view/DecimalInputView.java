package edu.usc.clicker.view;

import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.R;

/**
 * Created by ian on 9/15/15.
 */
public class DecimalInputView extends LinearLayout implements View.OnClickListener, View.OnLongClickListener {

    EditText editText;
    Vibrator vibrator;

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public DecimalInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.backspace).setOnClickListener(this);
//        findViewById(R.id.backspace).setOnTouchListener(new OnTouchListener() {
//            private Handler handler;
//            private boolean isHolding;
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        isHolding = true;
//                        if (handler != null) return true;
//                        handler = new Handler();
//                        handler.post(backspaceAction);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        isHolding = false;
//                        if (handler == null) return true;
//                        handler.removeCallbacks(backspaceAction);
//                        handler = null;
//                        break;
//                }
//                return false;
//            }
//
//            Runnable backspaceAction = new Runnable() {
//                @Override
//                public void run() {
//                    backspace();
//                    if (isHolding) {
//                        handler.postDelayed(this, 200);
//                    }
//                }
//            };
//        });
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            TextView textView = (TextView) v;
            insert(textView.getText());
        } else {
            backspace();
        }
        vibrator.vibrate(25);
    }

    private void insert(CharSequence text) {
        if (editText == null) return;

        editText.getText().insert(editText.getSelectionStart(), text);
    }

    private void backspace() {
        vibrator.vibrate(25);

        if (editText == null) return;
        if (editText.getSelectionStart() == 0) return;

        editText.getText().delete(editText.getSelectionStart() - 1, editText.getSelectionStart());
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
