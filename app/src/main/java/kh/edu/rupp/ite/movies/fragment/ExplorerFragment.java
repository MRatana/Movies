package kh.edu.rupp.ite.movies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import kh.edu.rupp.ite.movies.Activities.MovieDetail;
import kh.edu.rupp.ite.movies.adapters.Movie_Fav_Adapter;
import kh.edu.rupp.ite.movies.api.client.ApiClient;
import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.api.service.ApiService;
import kh.edu.rupp.ite.movies.databinding.FragmentExplorerBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExplorerFragment extends Fragment implements Movie_Fav_Adapter.OnItemClickListener{

    public FragmentExplorerBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExplorerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadListMovieFromServer();

    }
    private void loadListMovieFromServer(){

        Call<List<Movie>> tasks = ApiClient.get().getApiService().loadMoviesList();

        tasks.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    showMovieList(response.body());
                }
                else {
                    Toast.makeText(getContext(), "Movie received failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getContext(), "Product received failed", Toast.LENGTH_SHORT).show();
                Log.e("[ProductFragment]", "Load province failed " + t.getMessage());
                t.printStackTrace();
            }
        });

    }
    private void showMovieList(List<Movie> moviesList){
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false);
        binding.recyclerView.setLayoutManager(layoutManager);

        Movie_Fav_Adapter adapter = new Movie_Fav_Adapter(Movie_Fav_Adapter.MODE_EXPLORE);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.submitList(moviesList);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Movie movie, int position) {
        String[] array = new String[] {movie.getId(),movie.getTitle(),movie.getDescription(),movie.getImg(), movie.getRating()};
        Intent intent = new Intent(getContext(), MovieDetail.class);
        intent.putExtra("movie",array);
        startActivity(intent);
    }
}
