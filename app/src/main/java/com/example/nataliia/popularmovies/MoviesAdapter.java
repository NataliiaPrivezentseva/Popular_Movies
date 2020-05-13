package com.example.nataliia.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nataliia.popularmovies.model.Movie;
import java.util.List;

import androidx.annotation.NonNull;
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

        TextView itemText;

        MovieImageViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.movie_item_text);
        }

        void bind(int position) {
            itemText.setText(moviesList.get(position).getTitle());
        }
    }

}
