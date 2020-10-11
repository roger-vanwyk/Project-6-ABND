package com.example.android.newsreport;

import android.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;

import com.example.android.newsreport.*;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter (Context context, List<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView
                    = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView Title = (TextView) convertView.findViewById(R.id.article_title);
        TextView Type = (TextView) convertView.findViewById(R.id.article_type);
        TextView Date = (TextView) convertView.findViewById(R.id.article_date);
        TextView Url = (TextView) convertView.findViewById(R.id.article_url);

        News currentNews = getItem(position);
        Title.setText(currentNews.getTitle());
        Type.setText(currentNews.getType());
        Date.setText(currentNews.getDate());
        Url.setText(currentNews.getUrl());

        return convertView;
    }
}
