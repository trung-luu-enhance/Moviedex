package com.trunghtluu.moviedex.network;

import com.trunghtluu.moviedex.model.MovieSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/3/search/movie")
    Call<MovieSearch> getSearch(
        @Query("api_key") String api_key,
        @Query("query") String query
    );
}
