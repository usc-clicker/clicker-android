package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MyClassesListView extends ListView {
    public void refresh() {
        ((MyClassesAdapter) getAdapter()).refresh();
    }

    public MyClassesListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setAdapter(new MyClassesAdapter(context));
    }
}
