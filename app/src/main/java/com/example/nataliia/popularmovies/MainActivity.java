package com.example.nataliia.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final int NUMBER_OF_MOVIES = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecyclerView = findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);

        //TODO fix magic number
        layoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(layoutManager);

        moviesAdapter = new MoviesAdapter(NUMBER_OF_MOVIES);
        moviesRecyclerView.setAdapter(moviesAdapter);
    }
}
