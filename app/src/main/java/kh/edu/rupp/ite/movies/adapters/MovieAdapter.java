package kh.edu.rupp.ite.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.MovieCardBinding;

public class MovieAdapter extends ListAdapter<Movie , MovieAdapter.MovieViewHolder> {

    public MovieAdapter() {
        super(new DiffUtil.ItemCallback<Movie>() {
            @Override
            public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return newItem.getId() == oldItem.getId();
            }
        });
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieCardBinding binding = MovieCardBinding.inflate(inflater,parent,false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
            Movie movieCard = getItem(position);
            holder.bind(movieCard);
    }

    public static class MovieViewHolder extends ViewHolder {
        private MovieCardBinding binding;
        public MovieViewHolder(MovieCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie){
            binding.title.setText(movie.getTitle());
            binding.description.setText(movie.getDescription());
        }
    }
}
