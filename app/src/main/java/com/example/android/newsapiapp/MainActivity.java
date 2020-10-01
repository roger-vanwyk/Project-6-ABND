package com.example.android.newsapiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.newsapiapp.adapters.RecyclerViewAdapter;
import com.example.android.newsapiapp.News;
import com.example.android.newsapiapp.utils.NewsLoader;
import com.example.android.newsapiapp.FloatingSearchView;
import com.example.android.newsapiapp.OnSearchListener;
import com.example.android.newsapiapp.SearchSuggestion;
import com.example.android.newsapiapp.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static android.widget.Toast.makeText;
import static com.example.android.newsapiapp.R.id.floating_search_view;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    private static final String SEARCH_RESULTS = "newsSearchResults";


    private FloatingSearchView searchBarView;
    private RecyclerViewAdapter newsAdapter = new RecyclerViewAdapter (this, new ArrayList<News> ( ), new RecyclerViewAdapter.OnItemClickListener ( ) {

        @Override
        public void onItemClick(News news) {
            String url = news.getUrl ( );
            Intent i = new Intent (Intent.ACTION_VIEW);
            i.setData (Uri.parse (url));
            startActivity (i);
			return ;
		}
    });
    private View loadingIndicator;
    private Loader<ArrayList<News>> loader;

	private static final  floating_search_view = null;

	private static final  floating_search_view = null;

	private static final  floating_search_view = null;

    public MainActivity() {
        searchBarView = findViewById (floating_search_view);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        searchBarView.setOnHomeActionClickListener (
                new FloatingSearchView.OnHomeActionClickListener ( ) {
                });


        loadingIndicator = findViewById (R.id.loading_indicator);
        setLoadingIndicatorVisibilty (false);

        RecyclerView recyclerView = findViewById (R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getApplicationContext ( ));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration (recyclerView.getContext ( ),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration (dividerItemDecoration);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (newsAdapter);

        if (savedInstanceState != null) {
            News[] news = (News[]) savedInstanceState.getParcelableArray (SEARCH_RESULTS);
            assert news != null;
            ArrayList<News> list = new ArrayList<> (Objects.requireNonNull (news).length);
            Collections.addAll (list, news);
            newsAdapter.updateAdapterData (list);
            setSearchLabelVisibility (false);
        }

        setupSearchBar ( );


		return ;
	}

    private void setupSearchBar() {
		return ;
	}

    @NonNull
    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        setLoadingIndicatorVisibilty (true);
        setSearchLabelVisibility (false);
        return new NewsLoader (MainActivity.this, args.getString ("request_url"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<News>> loader, ArrayList<News> data) {
        this.loader = loader;
        setLoadingIndicatorVisibilty (false);
        if (data == null) {
            makeText (this, this.getResources ( ).getString (R.string.bad_server), Toast.LENGTH_SHORT).show ( );
            setSearchLabelVisibility (true);
            return;
        }
        setSearchLabelVisibility (data.size ( ) == 0);
        if (data.size ( ) == 0)
            makeText (this, this.getResources ( ).getString (R.string.no_news_found), Toast.LENGTH_SHORT).show ( );
        else {
            newsAdapter.updateAdapterData (data);
        }
		return 0;
	}

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<News>> loader) {
        newsAdapter.updateAdapterData (new ArrayList<News> ( ));
        setSearchLabelVisibility (true);
        setLoadingIndicatorVisibilty (false);
		return 0;
	}

    /**
     * Sets visibility of search label
     *
     * @param flag
     */
    public void setSearchLabelVisibility(boolean flag) {
        RelativeLayout searchLabel;
        searchLabel = this.findViewById (R.id.search_label);
        int id = flag ? View.VISIBLE : View.INVISIBLE;
        searchLabel.setVisibility (id);
		return ;
	}

    /**
     * Show/hide loading indicator
     *
     * @param flag
     */
    public void setLoadingIndicatorVisibilty(boolean flag) {
        int id = flag ? View.VISIBLE : View.INVISIBLE;
        loadingIndicator.setVisibility (id);
		return ;
	}

    @Override
    public void onBackPressed() {
        if (searchBarView.isSearchBarFocused ( )) {
            searchBarView.clearSuggestions ( );
            searchBarView.clearQuery ( );
            searchBarView.clearSearchFocus ( );
            return;
        }
        finish ( );
		return ;
	}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState (outState);
        ArrayList<News> searchList = newsAdapter.getAdapterData ( );
        News[] news = new News[searchList.size ( )];
        for (int i = 0; i < news.length; i++) {
            news[i] = searchList.get (i);
        }
        outState.putParcelableArray (SEARCH_RESULTS, news);
		return ;
	}


}
