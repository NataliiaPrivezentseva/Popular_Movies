package com.example.nataliia.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nataliia.popularmovies.model.Movie;
import com.example.nataliia.popularmovies.utils.ImageUriUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieImageViewHolder> {

    private List<Movie> moviesList;

    void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    MoviesAdapter(List<Movie> movieList) {
        moviesList = movieList;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout = R.layout.movie_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 2;
        lp.width = parent.getMeasuredWidth() / 2;
        view.setLayoutParams(lp);
        return new MovieImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieImageViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    class MovieImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        MovieImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
        }

        void bind(int position) {
            Picasso picasso = Picasso.get();
            picasso.load(ImageUriUtils.getImageStringUrl(moviesList.get(position).getPosterPath()))
                    .into(imageView);
        }
    }

}
