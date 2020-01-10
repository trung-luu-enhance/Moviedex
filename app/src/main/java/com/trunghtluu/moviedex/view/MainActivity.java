package com.trunghtluu.moviedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.trunghtluu.moviedex.R;
import com.trunghtluu.moviedex.adapter.MovieAdapter;
import com.trunghtluu.moviedex.model.MovieResult;
import com.trunghtluu.moviedex.model.MovieSearch;
import com.trunghtluu.moviedex.viewmodel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_search_et)
    EditText searchEditText;

    @BindView(R.id.main_search_bt)
    Button searchButton;

    @BindView(R.id.main_search_rv)
    RecyclerView searchRecyclerView;

    private MainViewModel mainViewModel;

    Observer observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        observer = new Observer<MovieSearch>() {
            @Override
            public void onChanged(MovieSearch movieSearch) {
                setupRV(movieSearch.getResults());
            }
        };

        mainViewModel.getResultLiveData().observe(MainActivity.this, observer);
    }

    private void setupRV(List<MovieResult> response) {
        System.out.println(response.get(0).getTitle());
        MovieAdapter adapter = new MovieAdapter(response);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        searchRecyclerView.addItemDecoration(itemDecoration);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        searchRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.main_search_bt)
    public void search() {
        mainViewModel.getSearches(searchEditText.getText().toString());
    }
}
