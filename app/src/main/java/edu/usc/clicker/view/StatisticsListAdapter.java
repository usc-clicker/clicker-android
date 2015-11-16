package edu.usc.clicker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.usc.clicker.ClickerApplication;
import edu.usc.clicker.R;
import edu.usc.clicker.model.QuizStatistics;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class StatisticsListAdapter extends BaseAdapter implements Callback<List<QuizStatistics>> {
    private final Context context;

    private final List<QuizStatistics> quizzes = new ArrayList<>();

    @Override
    public int getCount() {
        return quizzes.size();
    }

    @Override
    public Object getItem(int position) {
        return quizzes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_list_item, parent, false);
        }

        ((StatisticsListItem) convertView).bindQuizStatistics(quizzes.get(position));
        return convertView;
    }

    @Override
    public void onResponse(Response<List<QuizStatistics>> response, Retrofit retrofit) {
        quizzes.clear();
        quizzes.addAll(response.body());
        notifyDataSetChanged();
    }

    @Override
    public void onFailure(Throwable t) {

    }

    public StatisticsListAdapter(Context context, int sectionID) {
        this.context = context;

        ClickerApplication.CLICKER_API.getStats(ClickerApplication.LOGIN_HELPER.getEmail(context), sectionID).enqueue(this);
    }
}
