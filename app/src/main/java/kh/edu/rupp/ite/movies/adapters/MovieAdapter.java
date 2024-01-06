package kh.edu.rupp.ite.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.model.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.MovieCardBinding;

public class MovieAdapter extends ListAdapter<Movie , MovieAdapter.MovieViewHolder> {


    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }

    private OnItemClickListener listener;

    public MovieAdapter() {
        super(new DiffUtil.ItemCallback<Movie>() {
            @Override
            public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return newItem.getId().equals(oldItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieCardBinding binding = MovieCardBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movieCard = getItem(position);
        holder.bind(movieCard);

        // Set a click listener on the item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the position of the clicked item.
                int position = holder.getAdapterPosition();

                // Perform the desired action.
                if (listener != null) {
                    listener.onItemClick(movieCard, position);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class MovieViewHolder extends ViewHolder {
        private MovieCardBinding binding;

        public MovieViewHolder(MovieCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            binding.title.setText(movie.getTitle());
            binding.description.setText(movie.getDescription());
            Picasso.get().load(movie.getImg()).into(binding.image);
        }
    }
}
