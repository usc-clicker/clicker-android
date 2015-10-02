package edu.usc.clicker.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.usc.clicker.R;
import edu.usc.clicker.model.MultipleChoiceQuestion;

/**
 * Created by ian on 9/9/15.
 */
public class MultipleChoiceListAdapter extends RecyclerView.Adapter<MultipleChoiceItemHolder> {
    private MultipleChoiceQuestion question;
    private int selected = -1;

    public void setQuestion(MultipleChoiceQuestion question) {
        this.question = question;
    }

    @Override
    public MultipleChoiceItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View answer = LayoutInflater.from(viewGroup.getContext())
                                    .inflate(R.layout.multiple_choice_answer_item, viewGroup, false);
        return new MultipleChoiceItemHolder(answer, this);
    }

    @Override
    public void onBindViewHolder(MultipleChoiceItemHolder multipleChoiceItemHolder, int i) {
        multipleChoiceItemHolder.bindAnswer(question.getChoices().get(i), i);
        multipleChoiceItemHolder.setSelected(selected == i);
    }

    @Override
    public int getItemCount() {
        return question == null ? 0 : question.getChoices().size();
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    public int getSelectedItem() {
        return selected;
    }
}
