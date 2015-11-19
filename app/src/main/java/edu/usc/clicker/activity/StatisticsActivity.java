package edu.usc.clicker.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import edu.usc.clicker.R;
import edu.usc.clicker.model.Section;
import edu.usc.clicker.util.ClickerLog;
import edu.usc.clicker.view.StatisticsListView;

public class StatisticsActivity extends AppCompatActivity {

    public static void start(Context context, Section section) {
        Intent intent = new Intent(context, StatisticsActivity.class);
        intent.putExtra("section", section);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getIntent().hasExtra("section")) {
            ClickerLog.e("StatisticsActivity", "Activity started with invalid bundle!");
            finish();
        }

        Section section = getIntent().getParcelableExtra("section");

        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(section.getCourseID());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        StatisticsListView listView = (StatisticsListView) findViewById(R.id.listView);
        listView.setSection(Integer.parseInt(section.getSectionID()));
    }
}
