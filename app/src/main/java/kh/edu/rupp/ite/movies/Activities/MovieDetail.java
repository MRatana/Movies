package kh.edu.rupp.ite.movies.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.databinding.ActivityMovieDetailBinding;

public class MovieDetail extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}