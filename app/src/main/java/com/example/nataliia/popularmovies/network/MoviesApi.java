package com.example.nataliia.popularmovies.network;

import com.example.nataliia.popularmovies.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {

    String MOVIE = "movie";
    
    String API_KEY = "<place_your_API_key_here>";
    String PARAM_API_KEY = "api_key";

    String POPULAR = "popular";
    String TOP_RATED = "top_rated";

    @GET(MOVIE + "/" + POPULAR + "?" + PARAM_API_KEY + "=" + API_KEY)
    Call<MoviesResponse> getPopularMoviesResponse();

    @GET(MOVIE + "/" + TOP_RATED + "?" + PARAM_API_KEY + "=" + API_KEY)
    Call<MoviesResponse> getTopRatedMoviesResponse();

}
