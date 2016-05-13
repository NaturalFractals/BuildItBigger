package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.myapplication.backend.myApi.MyApi;

import java.io.IOException;

/**
 * This AsyncTask sends request to the Cloud Endpoints backend API.
 */
class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService;
    private EndpointsAsyncTaskListener mEndpointsAsyncTaskListener;

    static {
        myApiService = null;
    }

    private Context context;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {
        // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-1304.appspot.com/_ah/api/");
            //above url is address for project on google endpoint server

            myApiService = builder.build();
        }

        context = params[0].first;

        try {
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //On success make toast to UI (this will be in the main activity fragment)
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        //If listener exist, call onComplete method
        if(mEndpointsAsyncTaskListener != null) {
            mEndpointsAsyncTaskListener.onCompleted(result);
        }
    }

    public Context getContext() {
        return context;
    }

    public EndpointsAsyncTask setListener(EndpointsAsyncTaskListener listener) {
        mEndpointsAsyncTaskListener = listener;
        return this;
    }

    public interface EndpointsAsyncTaskListener {
        void onCompleted(String joke);
    }
}
