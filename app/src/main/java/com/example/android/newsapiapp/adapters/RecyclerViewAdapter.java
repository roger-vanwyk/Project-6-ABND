package com.example.android.newsapiapp.adapters;

// Created 01/2020 by Roger van Wyk.

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.R.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.newsapiapp.*;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<com.example.android.newsapiapp.adapters.NewsAdapter> {

    private ArrayList<News> items;
    private Activity activity;
    private OnItemClickListener mListener;

    public RecyclerViewAdapter(Activity activity, ArrayList<News> newsList, OnItemClickListener listener) {
        this.activity = activity;
        this.items = newsList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public com.example.android.newsapiapp.adapters.NewsAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ( )).inflate (R.layout.list_item, parent, false);
        return new com.example.android.newsapiapp.adapters.NewsAdapter (view);
    }

    @Override
    public void onBindViewHolder(com.example.android.newsapiapp.adapters.NewsAdapter holder, final int position) {

        News news = items.get (position);
        holder.newsCategory.setText (news.getCategory ( ));
        holder.newsTitle.setText (news.getTitle ( ));
        holder.newsType.setText (activity.getResources ( ).getString (R.string.type, news.getType ( )));
        holder.newsDate.setText (activity.getResources ( ).getString (R.string.date, news.getDate ( )));
        holder.bind (items.get (position), mListener);
		return ;
	}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size ( );
    }

    /**
     * Updates the adapter item list with new list
     *
     * @param newData list
     */
    public void updateAdapterData(ArrayList<News> newData) {
        this.items = newData;
        notifyDataSetChanged ( );
		return ;
	}

    public ArrayList<News> getAdapterData() {
        return this.items;
    }

    public interface OnItemClickListener {
        void onItemClick(News news);

    }
}
