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
import edu.usc.clicker.model.Section;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MyClassesAdapter extends BaseAdapter implements Callback<List<Section>> {
    private final Context context;
    private final List<Section> sections = new ArrayList<>();

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public Section getItem(int position) {
        return sections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_classes_item, parent, false);
        }

        ((MyClassesItem) convertView).bindSection(getItem(position));
        return convertView;
    }

    @Override
    public void onResponse(Response<List<Section>> response, Retrofit retrofit) {
        sections.clear();
        sections.addAll(response.body());
        notifyDataSetChanged();
    }

    @Override
    public void onFailure(Throwable t) {

    }

    public void refresh() {
        ClickerApplication.CLICKER_API.getUserSections(ClickerApplication.LOGIN_HELPER.getEmail(context)).enqueue(this);
    }

    public MyClassesAdapter(Context context) {
        this.context = context;

        refresh();
    }
}
