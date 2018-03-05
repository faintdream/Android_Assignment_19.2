package com.example.homepc.webservicemoviedb;

import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.homepc.webservicemoviedb.MainActivity.rv;
import static com.example.homepc.webservicemoviedb.Movie.movieList;

/**
 * In this class we make webservice calls
 */

public class MyWebService {
    //making JSONArray object static so that state is maintained
    static JSONArray rootElement;
    Movie movie;
    Handler handler;

    // Initializing object of Mainactivity, we would use it to get things done on MainActivity UI
    MainActivity mainActivity=new MainActivity();
    public MyWebService() {
    }

    //Method handling webservice calls, we make use of OkHttp a Third Party handler to make http calls
    public void CallMovieDB() {
        String myUrl = "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(myUrl).build();

        //here we make an async call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String myResponse = response.body().string().toString(); //myResponse holds http response

                try {

                    JSONObject object = new JSONObject(myResponse);  // passing response string to JSONObject
                    rootElement = object.getJSONArray("results"); //storing root element from JSONObject
                    for (int i = 0; i < rootElement.length(); i++) {  // simple loop to read elements
                        JSONObject objectInArray = rootElement.getJSONObject(i); //getting the position of element

                        Log.i("MYRV", objectInArray.getString("original_name"));
                        Log.i("MYRV", objectInArray.getString("vote_count"));
                        Log.i("MYRV", objectInArray.getString("genre_ids"));

                        //passing the elements at 'position' to Movie's constructor and simultaneously adding
                        // it to the List 'moveitList' which is of Type Movie
                        movieList.add(new Movie(
                                        objectInArray.getString("original_name"),
                                        objectInArray.getString("vote_count"),
                                        objectInArray.getString("genre_ids")
                                )
                        );
                    }

                  //since we cannot update UI from  OKHttp's own thread, creating another independent thread to handle this
                 // at MainActivity, since we cannot directly call MainActivity, i created an object of it, to do the
                 // needful.
                  mainActivity.runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          publishResult();
                      }
                  });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // getting stuff done oin MainActivity from here only because we have imported following packages:-
    // static com.example.homepc.webservicemoviedb.MainActivity.rv;
    // static com.example.homepc.webservicemoviedb.Movie.movieList;
    private void publishResult() {
        rv.setLayoutManager(new LinearLayoutManager(mainActivity));
        mainActivity.customListViewAdapter = new CustomListViewAdapter(Movie.movieList);
        rv.setAdapter(new CustomListViewAdapter(Movie.movieList));
    }


}
