package edu.usc.clicker.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.EnrollBody;
import edu.usc.clicker.model.Section;
import edu.usc.clicker.view.MyClassesListView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MyClassesActivity extends AppCompatActivity implements View.OnClickListener, Callback<Section>{

    private FloatingActionButton addFAB;
    private MyClassesListView listView;
    private Toolbar toolbar;

    public static void start(Context context) {
        Intent intent = new Intent(context, MyClassesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_classes);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        listView = (MyClassesListView) findViewById(R.id.listView);

        addFAB = (FloatingActionButton) findViewById(R.id.fab);
        addFAB.setOnClickListener(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        final AppCompatEditText editText = new AppCompatEditText(this);
        int padding = (int) (getResources().getDisplayMetrics().density * 24);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = padding;
        lp.rightMargin = padding;
        editText.setLayoutParams(lp);

        AlertDialog dialog = new AlertDialog.Builder(this)
                                            .setTitle(R.string.addClassDialogTitle)
                                            .setView(editText, padding, 0, padding, 0)
                                            .setMessage(R.string.addClassDialogMessage)
                                            .setPositiveButton(R.string.addClassDialogPositive, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    ClickerApplication.CLICKER_API.enroll(
                                                            new EnrollBody(ClickerApplication.LOGIN_HELPER.getEmail(MyClassesActivity.this), Integer.parseInt(editText.getText().toString())))
                                                            .enqueue(MyClassesActivity.this);
                                                }
                                            })
                                            .setNegativeButton(R.string.addClassDialogNegative, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .create();
        dialog.show();
    }

    @Override
    public void onResponse(Response<Section> response, Retrofit retrofit) {
        listView.refresh();
        refreshSections();
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Snackbar.make(findViewById(android.R.id.content), R.string.addClassNetworkFailure, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    private void refreshSections() {
        ClickerApplication.CLICKER_API
                .getUserSections(ClickerApplication.LOGIN_HELPER.getEmail(this))
                .enqueue(new Callback<List<Section>>() {
                    @Override
                    public void onResponse(Response<List<Section>> response, Retrofit retrofit) {
                        ClickerApplication.SECTION_HELPER.removeAllSections();
                        ClickerApplication.SECTION_HELPER.addSections(response.body());
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

}
