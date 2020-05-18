package com.example.nataliia.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nataliia.popularmovies.model.Movie;
import com.example.nataliia.popularmovies.utils.ImageUriUtils;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView originalTitle;
    private ImageView poster;
    private TextView releaseDate;
    private TextView rate;
    private TextView plot;

    private final static String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setTitle(R.string.movie_details_title);

        Intent intent = getIntent();
        if (intent == null) {
            Log.i(TAG, "Intent was null");
        } else {
            Movie movie = (Movie) Objects.requireNonNull(intent.getExtras()).get("movie");
            if (movie != null) {
                populateUI(movie);
            }
        }
    }

    private void populateUI(Movie movie) {
        originalTitle = findViewById(R.id.original_title);
        poster = findViewById(R.id.poster);
        releaseDate = findViewById(R.id.release_date);
        rate = findViewById(R.id.rate);
        plot = findViewById(R.id.plot);

        originalTitle.setText(movie.getOriginalTitle());

        Picasso picasso = Picasso.get();
        picasso.load(ImageUriUtils.getImageStringUrl(movie.getPosterPath()))
                .into(poster);

        releaseDate.setText(movie.getReleaseDate());
        rate.setText(String.valueOf(movie.getVoteAverage()));
        plot.setText(movie.getOverview());
    }
}
