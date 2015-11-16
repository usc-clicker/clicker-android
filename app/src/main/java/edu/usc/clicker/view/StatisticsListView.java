package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class StatisticsListView extends ListView {
    public StatisticsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSection(int sectionID) {
        setAdapter(new StatisticsListAdapter(getContext(), sectionID));
    }
}
