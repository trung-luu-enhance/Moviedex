package com.trunghtluu.moviedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trunghtluu.moviedex.R;
import com.trunghtluu.moviedex.model.MovieResult;
import com.trunghtluu.moviedex.utils.Constants;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public interface MovieAdapterDelegate{
        void movieSelected(MovieResult selected);
    }

    private MovieAdapterDelegate movieAdapterDelegate;
    private List<MovieResult> movieResultList;
    private Context applicationContext;

    public MovieAdapter(List<MovieResult> movieResultList) {
        this.movieResultList = movieResultList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        applicationContext = parent.getContext().getApplicationContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.nameTextView
                .setText("Title: " + movieResultList.get(position).getTitle());
        holder.yearTextView
                .setText("Release Date: " + movieResultList.get(position).getReleaseDate());
        holder.ratingTextView
                .setText("Rating: " + movieResultList.get(position).getVoteAverage());
        Glide.with(applicationContext)
                .load(Constants.IMAGE_BASE + movieResultList.get(position).getPosterPath())
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView ratingTextView;
        public TextView yearTextView;
        public ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_tv);
            yearTextView = itemView.findViewById(R.id.year_tv);
            ratingTextView = itemView.findViewById(R.id.rating_tv);
            posterImageView = itemView.findViewById(R.id.poster_iv);
        }
    }
}
