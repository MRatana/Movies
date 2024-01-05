package kh.edu.rupp.ite.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.ViewHolderSearchBinding;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Movie> searchResults;

    public SearchAdapter(List<Movie> searchResults) {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolderSearchBinding binding = ViewHolderSearchBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Movie movie = searchResults.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        private ViewHolderSearchBinding binding;

        public SearchViewHolder(ViewHolderSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            binding.textViewTitle.setText(movie.getTitle());
            binding.textViewDescription.setText(movie.getDescription());
            Picasso.get().load(movie.getImg()).into(binding.imgMovie);
        }
    }
}

