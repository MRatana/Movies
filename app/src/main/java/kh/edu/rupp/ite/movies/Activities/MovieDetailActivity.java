package kh.edu.rupp.ite.movies.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.databinding.ActivityMovieDetailBinding;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    private String[] movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        if (intent != null) {
            // Get the movie object from the intent.
            movie = intent.getStringArrayExtra("movie");
            binding.title.setText(movie[1]);
            binding.description.setText(movie[2]);
            Picasso.get().load(movie[3]).into(binding.image);
            binding.rate.setText(movie[5]);

        }
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.playMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ViewVideoActivity.class);
                intent.putExtra("video",movie[4]);
                startActivity(intent);
            }
        });

    }
}