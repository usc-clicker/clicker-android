package edu.usc.clicker.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import edu.usc.clicker.R;

public class MultipleChoiceItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View view;
    TextView letter;
    TextView answer;
    MultipleChoiceListAdapter adapter;
    int num;

    public void bindAnswer(String answer, int num) {
        this.answer.setText(answer);
        this.num = num;
        letter.setText(Character.toString((char) (num + 'A')));
    }
    
    public MultipleChoiceItemHolder(View itemView, MultipleChoiceListAdapter adapter) {
        super(itemView);
        this.view = itemView;
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
