package kh.edu.rupp.ite.movies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.adapters.SearchAdapter;
import kh.edu.rupp.ite.movies.api.client.ApiClient2;
import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.api.service.Service;
import kh.edu.rupp.ite.movies.databinding.FragmentSearchBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchAdapter adapter;
    private List<Movie> allMovies;
    private List<Movie> filteredMovies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize lists
        allMovies = new ArrayList<>();
        filteredMovies = new ArrayList<>();

        // Set up RecyclerView
        setupRecyclerView();

        // Set up search EditText
        setupSearchEditText();


    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new SearchAdapter(filteredMovies);
        binding.recyclerView.setAdapter(adapter);
    }


    private void setupSearchEditText() {
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString().trim();
                loadMoviesFromServer(query);

                binding.clearButton.setVisibility(query.isEmpty() ? View.GONE : View.VISIBLE);
            }
        });
        // Clear button click listener
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the search field
                binding.search.setText("");
                // Hide the clear button
                binding.clearButton.setVisibility(View.GONE);
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a FragmentTransaction
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

                // Replace the current fragment with HomeFragment
                transaction.replace(R.id.lyFragment, new HomeFragment());

                // Commit the transaction
                transaction.commit();
            }
        });


    }

    private void loadMoviesFromServer(String query) {
        Call<List<Movie>> task;

        // Check if a search query is provided
        if (!query.isEmpty()) {
            // Make an API call to search movies based on the query
            task = ApiClient2.get().getApiService().searchMovies(query);
        } else {
            // Make an API call to get all movies
            task = ApiClient2.get().getApiService().loadMoviesList();
        }

        task.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    allMovies = response.body();
                    filterMovies(query);
                } else {
                    // Handle API call failure
                    // For simplicity, show a toast message
                    showToast("Movie retrieval failed");
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                // Handle API call failure
                // For simplicity, show a toast message
                showToast("Movie retrieval failed");
            }
        });
    }

    private void filterMovies(String query) {
        filteredMovies.clear();

        for (Movie movie : allMovies) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredMovies.add(movie);
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}