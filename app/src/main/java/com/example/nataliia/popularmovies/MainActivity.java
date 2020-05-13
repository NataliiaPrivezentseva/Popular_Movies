package com.example.nataliia.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nataliia.popularmovies.model.Movie;
import com.example.nataliia.popularmovies.model.MoviesResponse;
import com.example.nataliia.popularmovies.network.MoviesApi;
import com.example.nataliia.popularmovies.network.RetrofitMoviesApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Movie> moviesList;

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
        moviesAdapter = new MoviesAdapter(moviesList);
        moviesRecyclerView.setAdapter(moviesAdapter);

        MoviesApi service = RetrofitMoviesApi.getRetrofitInstance().create(MoviesApi.class);
        Call<MoviesResponse> call = service.getPopularMoviesResponse();

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
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
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                //TODO implement this method
//                progressDialog.dismiss();
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
