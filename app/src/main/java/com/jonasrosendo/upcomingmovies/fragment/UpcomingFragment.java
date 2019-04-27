package com.jonasrosendo.upcomingmovies.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jonasrosendo.upcomingmovies.BuildConfig;
import com.jonasrosendo.upcomingmovies.R;
import com.jonasrosendo.upcomingmovies.adapter.AdapterUpcoming;
import com.jonasrosendo.upcomingmovies.api.Client;
import com.jonasrosendo.upcomingmovies.model.Movie;
import com.jonasrosendo.upcomingmovies.api.MovieData;
import com.jonasrosendo.upcomingmovies.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {


    public UpcomingFragment() {
        // Required empty public constructor
    }

    private RecyclerView recycler_upcoming;
    private AdapterUpcoming adapterUpcoming;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        recycler_upcoming = view.findViewById(R.id.recycler_upcoming);
        //set recycler params
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recycler_upcoming.setLayoutManager(layoutManager);
        recycler_upcoming.setHasFixedSize(true);
        recycler_upcoming.setItemAnimator(new DefaultItemAnimator());

        retrieveMovies();

        return view;
    }

    //method that get data from the api
    public void retrieveMovies(){

        Client client = new Client();

        MovieData api_service = client.getClient().create(MovieData.class);
        Call<Movie> call = api_service.downloadMovie(BuildConfig.THEMOVIE_API_KEY, "pt-BR");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    //retrieve data and config recycler adapter
                    List<Results> movie = response.body().getResultsList();
                    adapterUpcoming = new AdapterUpcoming(getContext(), movie);
                    recycler_upcoming.setAdapter(adapterUpcoming);

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("teste", "Fail: " + t.getMessage());
            }
        });
    }
}
