package com.codepath.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by HENRY on 7/13/2016.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public static class ViewHolder{
        TextView originalTitle;
        TextView overview;
        ImageView imageMovie;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //get the data item for position
        Movie movie = getItem(position);

        //checkthe existing view being reused
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.originalTitle = (TextView) convertView.findViewById(R.id.tvTitle);

            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

            viewHolder.imageMovie = (ImageView) convertView.findViewById(R.id.idMovieImage);

            //clear out image from convertView
            viewHolder.imageMovie.setImageResource(0);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.originalTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

        //populate data
        viewHolder.originalTitle.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.imageMovie);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.imageMovie);
        }


        //return the view
        return convertView;
    }
}
