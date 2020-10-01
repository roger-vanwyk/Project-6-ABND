package com.example.android.newsapiapp.utils;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

// Created 01/2020 by Roger van Wyk

public class NewsLoader extends AsyncTaskLoader<ArrayList<com.example.android.newsapiapp.utils.News>> {

    private String dataSource;

    public NewsLoader(Context context, String dataSource) {
        super(context);
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<com.example.android.newsapiapp.utils.News> loadInBackground() {
        com.example.android.newsapiapp.utils.JSONParser jsonParser = new com.example.android.newsapiapp.utils.JSONParser (this.dataSource);
        return jsonParser.getResponseData();
    }
}