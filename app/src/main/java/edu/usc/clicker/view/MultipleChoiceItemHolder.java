package edu.usc.clicker.view;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.R;

public class MultipleChoiceItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    LinearLayout view;
    TextView letter;
    TextView answer;
    MultipleChoiceListAdapter adapter;
    int num;

    public void bindAnswer(String answer, int num, boolean showAnswer) {
        if (showAnswer) {
            this.answer.setText(answer);
            this.view.setGravity(Gravity.START);
        } else {
            this.answer.setText("");
            this.view.setGravity(Gravity.CENTER);
        }
        this.num = num;
        letter.setText(Character.toString((char) (num + 'A')));
    }
    
    public MultipleChoiceItemHolder(View itemView, MultipleChoiceListAdapter adapter) {
        super(itemView);
        this.view = (LinearLayout) itemView;
        this.adapter = adapter;
        letter = (TextView) itemView.findViewById(R.id.letter);
        answer = (TextView) itemView.findViewById(R.id.answer);

        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        adapter.setSelected(num);
    }

    public void setSelected(boolean selected) {
        view.setSelected(selected);
    }
}
