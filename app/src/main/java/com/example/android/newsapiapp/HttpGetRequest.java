package com.example.android.newsapiapp;

// Created 01/2020 by Roger van Wyk

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpGetRequest {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECTION_TIMEOUT = 10000;


    public static String getResponseString(String params) {
        String stringUrl = params;
        String result;

        String inputLine;
        try {
            //Create a URL object to hold your url
            URL myUrl = new URL(stringUrl);
            //Create an http connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            //Setting methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to your url
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check the line status
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Closing your InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set your result equal to your stringBuilder
                result = stringBuilder.toString();
            } else {
                return null;
            }

        } catch (Exception e) {
            Log.e(e.getClass().getName(), e.getMessage(), e);
            Log.e("ERROR :", "Error connecting to network");
            return "";
        }
        return result;
    }

}
