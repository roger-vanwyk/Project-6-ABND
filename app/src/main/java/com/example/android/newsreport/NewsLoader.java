package com.example.android.newsreport;

import android.content.Context;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.example.android.newsreport.*;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

        @Override
        protected void onStartLoading () {
            super.onStartLoading();
            forceLoad();
			return ;
        }
// Load in background
        @Override
        public List<News> loadInBackground () {
            List<News> newsArticlesList = (List<News>) null;
            try {
                URL url = QueryUtils.createUrl();
                String jsonResponse = QueryUtils.makeHttpRequest(url);
                newsArticlesList = QueryUtils.parseJson(jsonResponse);
				// Catch IOExeption if something goes wrong with parse
            } catch (IOException e) {
                Log.e("Queryutils", "Error Loader LoadInBackground: ", e);
            }
            return newsArticlesList;
        }
    }
