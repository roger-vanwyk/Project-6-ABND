package com.example.android.newsapiapp.adapters;

// Created 23/01/2020 by Roger van Wyk

import android.*;
import android.view.*;
import android.widget.*;
import com.example.android.newsapiapp.*;

import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

class NewsViewHolder extends ViewHolder {

    TextView newsCategory, newsTitle, newsType, newsDate;

    /**
     * @param itemView
     */
    NewsViewHolder(View itemView) {
        super(itemView);

        newsCategory = itemView.findViewById(R.id.category);
        newsTitle = itemView.findViewById(R.id.title);
        newsType = itemView.findViewById(R.id.type);
        newsDate = itemView.findViewById(R.id.date);
    }

    void bind(final News news, final RecyclerViewAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(news);
				return ;
			}
        });
		return ;
	}
}
