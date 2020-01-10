package com.trunghtluu.moviedex.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.trunghtluu.moviedex.model.MovieSearch;
import com.trunghtluu.moviedex.network.MovieRetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private MovieRetrofitInstance movieRetrofitInstance = new MovieRetrofitInstance();
    private MutableLiveData<MovieSearch> resultLiveData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSearches(String query){
        movieRetrofitInstance.getSearch(query).enqueue(new Callback<MovieSearch>() {
            @Override
            public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                Log.d("TAG_X", "pass");
                resultLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieSearch> call, Throwable t) {
                System.out.println("Fail");
                Log.d("TAB_X", "Fail");
            }
        });
    }

    public MutableLiveData<MovieSearch> getResultLiveData() {
        return resultLiveData;
    }
}
