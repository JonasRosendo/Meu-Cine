package com.jonasrosendo.upcomingmovies.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonasrosendo.upcomingmovies.R;
import com.jonasrosendo.upcomingmovies.model.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUpcoming extends RecyclerView.Adapter<AdapterUpcoming.UpcomingViewHolder> {

    private Context context;
    private List<Results> movieList;

    public AdapterUpcoming(Context context, List<Results> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcoming_layout, viewGroup, false);
        return new UpcomingViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int i) {

        holder.txv_title.setText(movieList.get(i).getTitle());
        holder.txv_description.setText(movieList.get(i).getOverview());
        holder.txv_release.setText("Data de estréia: " + movieList.get(i).getReleaseDate());

        Picasso.get().load(movieList.get(i).getPosterPath()).placeholder(android.R.drawable.stat_sys_download).into(holder.imgv_poster);

        Log.d("test", "\n" + movieList.get(i).getTitle());
        Log.d("test", "\n" + movieList.get(i).getOverview());
        Log.d("test", "\n" + movieList.get(i).getReleaseDate());
        Log.d("test", "\n" + movieList.get(i).getPosterPath() + "\n");
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder {

        TextView txv_title, txv_description, txv_release;
        ImageView imgv_poster;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);

            txv_title = itemView.findViewById(R.id.txv_title);
            txv_description = itemView.findViewById(R.id.txv_description);
            txv_release = itemView.findViewById(R.id.txv_release);
            imgv_poster = itemView.findViewById(R.id.imgv_poster);

        }
    }
}
