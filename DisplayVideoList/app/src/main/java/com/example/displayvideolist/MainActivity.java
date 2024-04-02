package com.example.displayvideolist;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.credentials.Action;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //public  static String JSON_URL = "http://localhost:3001/list";
    public  static String JSON_URL = "https://4kl71.wiremockapi.cloud/movies";
    //private var binding: ActivityMainBinding;

    List<MovieModelClass> movieList;
    RecyclerView recyclerView;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        GetData getData = new GetData();
        getData.execute(JSON_URL);



    }






    public class GetData extends AsyncTask<String,String,String>
    {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    //urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    int data = isr.read();
                    while(data != -1)
                    {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;




                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }


                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("movies");
                for( int i = 0; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Log.v("TEST", String.valueOf(jsonObject1));

                    MovieModelClass model = new MovieModelClass();
                    model.setDescription(jsonObject1.getString( "description"));
                    model.setThumbnail(jsonObject1.getString( "thumb"));
                    model.setSubtitle(jsonObject1.getString( "subtitle"));
                    model.setUrl(jsonObject1.getString( "url"));
                    model.setTitle(jsonObject1.getString( "title"));
                    movieList.add(model);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            PutDataIntoRecyclerView( movieList);


        }
    }
    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList)
    {
        Adaptery adaptery = new Adaptery( this,movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adaptery);

    }
}