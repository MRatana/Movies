package kh.edu.rupp.ite.movies.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.adapters.MovieAdapter;
import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.FragmentHomeBinding;
import kh.edu.rupp.ite.movies.help.ShowFragment;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<Movie> movieList = new ArrayList<Movie>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        ShowFragment.show(new FilterCategory(),getChildFragmentManager(),R.id.filter_fragment);

        movieList.add(new Movie("5","hjjjj","kkkkkk","99"));
        movieList.add(new Movie("5","hjjjj","kkkkkk","99"));
        movieList.add(new Movie("5","hjjjj","kkkkkk","99"));
        movieList.add(new Movie("5","hjjjj","kkkkkk","99"));

        showNew(movieList);
        showPopular(movieList);
        return binding.getRoot();
    }


    private void showNew(List<Movie> movieList){
        //layout management
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.newReleaseList.setLayoutManager(linearLayoutManager);

        //adapter
        MovieAdapter adapter = new MovieAdapter();
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
        adapter.submitList(movieList);

        binding.popularList.setAdapter(adapter);
    }
}
