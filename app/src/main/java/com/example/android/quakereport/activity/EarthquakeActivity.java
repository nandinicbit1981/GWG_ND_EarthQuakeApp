/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.adapter.QuakeListAdapter;
import com.example.android.quakereport.model.QuakeModel;
import com.example.android.quakereport.util.QueryUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        new EarthQuakeAsyncTask().execute(urlString);


    }


    private  class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<QuakeModel>> {

        @Override
        protected List<QuakeModel> doInBackground(String... urlString) {
            List<QuakeModel> quakes = new ArrayList<>();
            URL url = null;
            try {
                url = new URL(urlString[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStream inputStream = null;
            String jsonResponse = "";
            try {
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.connect();
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return QueryUtils.extractEarthquakes(jsonResponse);
        }


        @Override
        protected void onPostExecute(List<QuakeModel> quakeModels) {

            // Find a reference to the {@link ListView} in the layout
            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            QuakeListAdapter adapter = new QuakeListAdapter(
                    EarthquakeActivity.this, R.layout.listitem,  quakeModels);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);
        }
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }



}
