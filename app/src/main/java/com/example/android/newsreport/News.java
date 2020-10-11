package com.example.android.newsreport;

public class News {

    private String mTitle;

    private String mType;

    private String mDate;

    private String mAuthor;

    private String mUrl;

    public News(String title , String type , String date , String author , String url) {
        mTitle = title;
        mType = type ;
        mDate = date;
        mAuthor = author;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }
}
