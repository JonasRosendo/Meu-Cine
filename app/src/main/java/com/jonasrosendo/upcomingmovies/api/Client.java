package com.jonasrosendo.upcomingmovies.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    //this class creates the retrofit configuration
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_IMG_URL = "https://image.tmdb.org/t/p/w500";
    private Retrofit retrofit = null;

    public Retrofit getClient(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
