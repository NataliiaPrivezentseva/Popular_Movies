package com.example.nataliia.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nataliia.popularmovies.model.Movie;
import com.example.nataliia.popularmovies.model.MoviesResponse;
import com.example.nataliia.popularmovies.network.MoviesApi;
import com.example.nataliia.popularmovies.network.RetrofitMoviesApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Movie> moviesList;

    private static boolean sortByTopRated;
    private static Call<MoviesResponse> call;

    private static final int COLUMN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecyclerView = findViewById(R.id.movies_recycler_view);
        moviesRecyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, COLUMN_COUNT);
        moviesRecyclerView.setLayoutManager(layoutManager);

        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(moviesList, this);
        moviesRecyclerView.setAdapter(moviesAdapter);

        Spinner spinner = findViewById(R.id.sort_by_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_by_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortByTopRated = position == 1;
                MoviesApi service = RetrofitMoviesApi.getRetrofitInstance().create(MoviesApi.class);

                if (sortByTopRated) {
                    call = service.getPopularMoviesResponse();
                } else {
                    call = service.getTopRatedMoviesResponse();
                }

                loadMoviesList(call);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadMoviesList(Call<MoviesResponse> call) {
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                if (moviesResponse != null) {
                    moviesList = moviesResponse.getResults();
                    if (moviesList != null && !moviesList.isEmpty()) {
                        moviesAdapter.setMoviesList(moviesList);
                        moviesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                //TODO implement this method
//                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
