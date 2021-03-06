package com.example.flickr_app;

import android.os.AsyncTask;
import android.util.Log;
import android.util.StringBuilderPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//We will create enum to hold the result codes if something goes wrong.
enum DownloadStatus { IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK }

public class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    private DownloadStatus mDownloadStatus;

    public GetRawData() {
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if(strings == null)
        {
            mDownloadStatus = DownloadStatus.NOT_INITIALISED;
            return null;
        }
// Log.e-> Logs an error in the logcat.
        try {
            mDownloadStatus = DownloadStatus.PROCESSING;//because we are in the process of downloading the URL.
            URL url = new URL(strings[0]); //We are getting URL parsed to this method so we can process that.

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d(TAG, "doInBackground: The response code was "+ response);

            StringBuilder result =new StringBuilder();// Will contain the data that we are going to download ultimately.

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


        }catch (MalformedURLException e)
        {
            Log.e(TAG, "doInBackground: INVALID URL"+ e.getMessage());
        }catch (IOException e)
        {
            Log.e(TAG, "doInBackground: IO Exception Reading Data"+ e.getMessage());
        }catch (SecurityException e)
        {
            Log.e(TAG, "doInBackground: Security Exception. Needs Permission?"+ e.getMessage());
        }

        return null;
    }
}
