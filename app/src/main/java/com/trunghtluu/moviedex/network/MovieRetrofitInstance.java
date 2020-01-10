package com.trunghtluu.moviedex.network;

import android.graphics.Movie;
import android.util.Log;

import com.trunghtluu.moviedex.model.MovieSearch;
import com.trunghtluu.moviedex.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofitInstance {


    private MovieService movieService;

    public MovieRetrofitInstance(){
        movieService = createMovieService(getInstance());
    }

    private Retrofit getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        return new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private MovieService createMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    public Call<MovieSearch> getSearch(String query) {
        return movieService.getSearch(Constants.API_KEY, query);
    }
}
