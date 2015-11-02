package edu.usc.clicker.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import edu.usc.clicker.model.MultipleChoiceQuestion;

/**
 * Created by ian on 9/9/15.
 */
public class MultipleChoiceList extends RecyclerView {
    public MultipleChoiceList(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        setAdapter(new MultipleChoiceListAdapter(context));
    }

    public void setQuestion(MultipleChoiceQuestion question) {
        ((MultipleChoiceListAdapter) getAdapter()).setQuestion(question);
    }

    public int getSelectedItem() {
        return ((MultipleChoiceListAdapter) getAdapter()).getSelectedItem();
    }
}
