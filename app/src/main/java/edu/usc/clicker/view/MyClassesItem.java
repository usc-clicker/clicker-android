package edu.usc.clicker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.usc.clicker.R;
import edu.usc.clicker.model.Section;

public class MyClassesItem extends LinearLayout {
    private TextView lecturer;
    private TextView name;
    private TextView id;

    public void bindSection(Section section) {
        lecturer.setText(section.getInstructor());
        name.setText(section.getCourseID());
        id.setText(section.getSectionID());
    }

    public MyClassesItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        lecturer = (TextView) findViewById(R.id.lecturer);
        name = (TextView) findViewById(R.id.name);
        id = (TextView) findViewById(R.id.id);
    }
}
