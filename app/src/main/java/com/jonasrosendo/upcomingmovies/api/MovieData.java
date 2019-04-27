package com.jonasrosendo.upcomingmovies.api;

import com.jonasrosendo.upcomingmovies.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieData {

    @GET("movie/upcoming")
    Call<Movie> downloadMovie(@Query("api_key") String api_key, @Query("language") String language);
}
