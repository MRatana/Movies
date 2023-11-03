package kh.edu.rupp.ite.movies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import kh.edu.rupp.ite.movies.Activities.MovieDetail;
import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.adapters.MovieAdapter;
import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.api.service.Service;
import kh.edu.rupp.ite.movies.databinding.FragmentHomeBinding;
import kh.edu.rupp.ite.movies.help.ShowFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements MovieAdapter.OnItemClickListener {
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        ShowFragment.show(new FilterCategory(),getChildFragmentManager(),R.id.filter_fragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMovie();
    }

    private void getMovie(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<List<Movie>> task = retrofit.create(Service.class).getMovie();

        task.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    showNew(response.body());
                    showPopular(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });
    }
    private void showNew(List<Movie> movieList){
        //layout management
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.newReleaseList.setLayoutManager(linearLayoutManager);

        MovieAdapter adapter = new MovieAdapter();
        adapter.setOnItemClickListener(this);
        adapter.submitList(movieList);

        binding.newReleaseList.setAdapter(adapter);

    }

    private void showPopular(List<Movie> movieList){
        //layout management
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.popularList.setLayoutManager(linearLayoutManager);

        //adapter
        MovieAdapter adapter = new MovieAdapter();
        adapter.setOnItemClickListener(this);
        adapter.submitList(movieList);

        binding.popularList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(Movie movie, int position) {
        String[] array = new String[] {movie.getId(),movie.getTitle(),movie.getDescription(),movie.getImg(), movie.getRating()};
        Intent intent = new Intent(getContext(), MovieDetail.class);
        intent.putExtra("movie",array);
        startActivity(intent);
    }
}

