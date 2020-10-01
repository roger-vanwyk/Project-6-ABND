package com.example.android.newsapiapp.adapters;

import android.app.Activity;

import com.example.android.newsapiapp.News;

import java.util.ArrayList;

class RecyclerViewAdapterImpl extends RecyclerViewAdapter {
    public RecyclerViewAdapterImpl(Activity activity, ArrayList<News> newsList, OnItemClickListener listener) {
        super (activity, newsList, listener);
    }
}
