package edu.usc.clicker.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.activity.MyClassesActivity;
import edu.usc.clicker.model.EnrollBody;
import edu.usc.clicker.model.Section;

public class MyClassesListView extends ListView implements AdapterView.OnItemLongClickListener {
    public void refresh() {
        ((MyClassesAdapter) getAdapter()).refresh();
    }

    public MyClassesListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setAdapter(new MyClassesAdapter(context));

        setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setItems(new CharSequence[]{getResources().getString(R.string.drop_section)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Section section = ((MyClassesAdapter) getAdapter()).getItem(position);
                        ClickerApplication.CLICKER_API.unenroll(new EnrollBody(ClickerApplication.getLoginHelper().getEmail(getContext()), Long.parseLong(section.getSectionID()))).enqueue((MyClassesActivity) getContext());
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
        return true;
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
        menu.add(0, 0, 0, R.string.drop_section);
    }


}
