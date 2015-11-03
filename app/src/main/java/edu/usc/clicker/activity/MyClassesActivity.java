package edu.usc.clicker.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.LinearLayout;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.EnrollBody;
import edu.usc.clicker.view.MyClassesAdapter;
import edu.usc.clicker.view.MyClassesListView;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MyClassesActivity extends AppCompatActivity implements View.OnClickListener, Callback<ResponseBody> {

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

        listView = (MyClassesListView) findViewById(R.id.listView);

        addFAB = (FloatingActionButton) findViewById(R.id.fab);
        addFAB.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

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
                                                            new EnrollBody(ClickerApplication.LOGIN_HELPER.getEmail(MyClassesActivity.this), Integer.parseInt(editText.getText().toString()))).enqueue(MyClassesActivity.this);
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
    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
        try {
            if (response.body().string().contains("ok")) {
                listView.refresh();
            } else {
                Snackbar.make(findViewById(android.R.id.content), R.string.addClassFailure, Snackbar.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(findViewById(android.R.id.content), R.string.addClassNetworkFailure, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_classes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.statistics) {
            StatisticsActivity.start(this);
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

}
