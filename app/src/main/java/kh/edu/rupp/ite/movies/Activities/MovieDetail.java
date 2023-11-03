package kh.edu.rupp.ite.movies.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.ActivityMovieDetailBinding;

public class MovieDetail extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        if (intent != null) {
            // Get the movie object from the intent.
            String[] movie = intent.getStringArrayExtra("movie");
            binding.title.setText(movie[1]);
            binding.description.setText(movie[2]);
            Picasso.get().load(movie[3]).into(binding.image);
            binding.rate.setText(movie[4]);

            // Do something with the movie object.
        }
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}