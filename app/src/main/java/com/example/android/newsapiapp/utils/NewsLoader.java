package com.example.android.newsapiapp.utils;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

import com.example.android.newsapiapp.*;

// Created 01/2020 by Roger van Wyk

public class NewsLoader extends AsyncTaskLoader<ArrayList<com.example.android.newsapiapp.News>> {

    private String dataSource;

    public NewsLoader(Context context, String dataSource) {
        super(context);
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<com.example.android.newsapiapp.News> loadInBackground() {
        com.example.android.newsapiapp.utils.JSONParser jsonParser = new com.example.android.newsapiapp.utils.JSONParser (this.dataSource);
        return jsonParser.getResponseData();
    }
}
