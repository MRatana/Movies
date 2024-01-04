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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.ite.movies.Activities.MovieDetailActivity;
import kh.edu.rupp.ite.movies.adapters.Movie_Fav_Adapter;
import kh.edu.rupp.ite.movies.api.client.ApiClient2;
import kh.edu.rupp.ite.movies.api.model.Movie;

import kh.edu.rupp.ite.movies.databinding.FragmentFavoriteBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment implements Movie_Fav_Adapter.OnItemClickListener {

    private FragmentFavoriteBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadListMovieFromServer();

    }
    private void loadListMovieFromServer(){

        Call<List<Movie>> tasks = ApiClient2.get().getApiService().loadMoviesList();

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

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3, GridLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);

        Movie_Fav_Adapter adapter = new Movie_Fav_Adapter(Movie_Fav_Adapter.MODE_FAVORITE);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.submitList(moviesList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Movie movie, int position) {
        String[] array = new String[] {movie.getId(),movie.getTitle(),movie.getDescription(),movie.getImg(),movie.getVideo(), movie.getRating()};
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie",array);
        startActivity(intent);
    }

    public void setBinding(FragmentFavoriteBinding binding) {
        this.binding = binding;
    }
}


