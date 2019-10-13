package com.example.amuna;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<Data> dataList;


    public ListAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;


    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.user,null);
        TextView NAME = (TextView) v.findViewById(R.id.name);
        TextView ID = (TextView) v.findViewById(R.id.uid);
        TextView Rating = (TextView) v.findViewById(R.id.rating);
        ID.setText(dataList.get(position).getMember_id());
        NAME.setText(dataList.get(position).getMember_name());
        Rating.setText(dataList.get(position).getMember_rating());
        v.setTag(dataList.get(position).getMember_id());
        return v;
    }
}