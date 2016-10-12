package com.jamesvuong.week1_flicks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jvuonger on 10/11/16.
 */

public class MovieListingAdapter extends ArrayAdapter<Movie> {

    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    private static String IMAGE_POSTER_WIDTH = "w154";

    private static class ViewHolder {
        ImageView moviePoster;
        TextView movieTitle;
        TextView movieOverview;
    }

    public MovieListingAdapter(Context context, List<Movie> movieList) {
        super(context, R.layout.movie_item_view, movieList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.movie_item_view, parent, false);

            viewHolder.moviePoster = (ImageView) convertView.findViewById(R.id.movie_poster);
            viewHolder.movieTitle = (TextView) convertView.findViewById(R.id.movie_title);
            viewHolder.movieOverview = (TextView) convertView.findViewById(R.id.movie_overview);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set Poster Image
        Picasso.with(getContext()).load(IMAGE_BASE_URL+"/"+IMAGE_POSTER_WIDTH+"/"+movie.getPosterPath()).into(viewHolder.moviePoster);
        viewHolder.movieTitle.setText(movie.getTitle());
        viewHolder.movieOverview.setText(movie.getOverview());

        return convertView;
    }

}
