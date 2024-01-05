package kh.edu.rupp.ite.movies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.ite.movies.Activities.MovieDetailActivity;
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
    private List<Movie> allMovies;
    private List<Movie> filteredMovies;
    private MovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void showSearchFragment() {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.lyFragment, new SearchFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ShowFragment.show(new FilterCategory(), getChildFragmentManager(), R.id.filter_fragment);
        setupRecyclerView();
        getMovie();
        //setupSearch();
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchFragment();
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.newReleaseList.setLayoutManager(linearLayoutManager);

        adapter = new MovieAdapter();
        adapter.setOnItemClickListener(this);
        binding.newReleaseList.setAdapter(adapter);
    }

    private void getMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<List<Movie>> task = retrofit.create(Service.class).getMovie();

        task.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    allMovies = response.body();
                    filteredMovies = new ArrayList<>(allMovies);
                    adapter.submitList(filteredMovies);
                    showNew(allMovies);
                    showPopular(allMovies);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                // Handle failure if needed
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

//    private void setupSearch() {
//        binding.search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
//                // Not used in this example
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                filterMovies(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // Not used in this example
//            }
//        });
//    }
//
//    private void filterMovies(String query) {
//        filteredMovies.clear();
//
//        for (Movie movie : allMovies) {
//            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())
//                    || movie.getDescription().toLowerCase().contains(query.toLowerCase())) {
//                filteredMovies.add(movie);
//            }
//        }
//
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public void onItemClick(Movie movie, int position) {
        String[] array = new String[]{movie.getId(), movie.getTitle(), movie.getDescription(), movie.getImg(), movie.getVideo(), movie.getRating()};
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("movie", array);
        startActivity(intent);
    }

}
