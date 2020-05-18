package com.example.nataliia.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nataliia.popularmovies.model.Movie;
import com.example.nataliia.popularmovies.utils.ImageUriUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieImageViewHolder> {

    private List<Movie> moviesList;
    private final MoviesAdapterOnClickHandler movieClickHandler;

    void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public interface MoviesAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    MoviesAdapter(List<Movie> movieList, MoviesAdapterOnClickHandler clickHandler) {
        moviesList = movieList;
        movieClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public MoviesAdapter.MovieImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout = R.layout.movie_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);

        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        if (context.getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT) {
            lp.height = parent.getMeasuredHeight() / MainActivity.COLUMN_COUNT_PORTRAIT;
            lp.width = parent.getMeasuredWidth() / MainActivity.COLUMN_COUNT_PORTRAIT;
        } else {
            lp.width = parent.getMeasuredWidth() / MainActivity.COLUMN_COUNT_LANDSCAPE;
        }

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


    class MovieImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;

        MovieImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
            imageView.setOnClickListener(this);
        }

        void bind(int position) {
            Picasso picasso = Picasso.get();
            picasso.load(ImageUriUtils.getImageStringUrl(moviesList.get(position).getPosterPath()))
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie selectedMovie = moviesList.get(adapterPosition);
            movieClickHandler.onClick(selectedMovie);
        }
    }

}
