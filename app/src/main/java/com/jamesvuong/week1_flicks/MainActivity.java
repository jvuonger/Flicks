package com.jamesvuong.week1_flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getName();
    private ListView mListView;
    private MovieListingAdapter mMovieListingAdapter;
    private List<Movie> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = new ArrayList<Movie>();
        mMovieListingAdapter = new MovieListingAdapter(this, mMovieList);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mMovieListingAdapter);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("api_key", "a07e22bc18f5cb106bfe4cc1f83ad8ed");
        client.get("https://api.themoviedb.org/3/movie/now_playing", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e(TAG, "Fetch movies error" + statusCode + " " + responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseString);
                            mMovieListingAdapter.addAll(Movie.fromJsonArray(jsonObject.getJSONArray("results")));
                            Log.i(TAG, "onSuccess: " + mMovieList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
