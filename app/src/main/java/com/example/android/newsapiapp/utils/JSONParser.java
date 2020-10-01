package com.example.android.newsapiapp.utils;

// Created 01/2020 by Roger van Wyk

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class JSONParser {

    private String dataSource;

    JSONParser(String dataSource) {
        this.dataSource = dataSource;
    }

    ArrayList<com.example.android.newsapiapp.News> getResponseData() {
        ArrayList<com.example.android.newsapiapp.News> newsList = new ArrayList<com.example.android.newsapiapp.News> ( );
        String responseJSON = com.example.android.newsapiapp.utils.HttpGetRequest.getResponseString (this.dataSource);
        if (responseJSON == null) {
            return null;
        }
        try {
            JSONObject reader = new JSONObject (responseJSON);
            JSONObject response = reader.getJSONObject ("response");
            JSONArray resultArray = response.getJSONArray ("results");
            for (int i = 0; i < resultArray.length ( ); i++) {
                JSONObject news = resultArray.getJSONObject (i);
                String title = news.getString ("webTitle");
                String type = news.getString ("type");
                String date = news.getString ("webPublicationDate");
                String category = news.getString ("sectionName");
                String url = news.getString ("webUrl");
                com.example.android.newsapiapp.News n = new com.example.android.newsapiapp.News (category, title, type, date, url);
                newsList.add (n);
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
            return new ArrayList<> ( );
        }
        return newsList;
    }
}
