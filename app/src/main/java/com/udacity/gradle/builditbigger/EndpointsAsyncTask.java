package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.example.v.maletskiy.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by v.maletskiy on 11.10.2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private String LOG_TAG = this.getClass().getSimpleName();

    public OnAsyncTaskCompleted callback;

    public EndpointsAsyncTask(OnAsyncTaskCompleted onAsyncTaskCompleted) {
        this.callback = onAsyncTaskCompleted;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(
                            new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                    request.setDisableGZipContent(true);
                                }
                            }
                    );
            myApiService = builder.build();
        }
        try {
            Log.v(LOG_TAG, "Trying to ping myApiService");
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.v(LOG_TAG, "myApiService error " + e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        callback.onCompleted(result);
    }


}
