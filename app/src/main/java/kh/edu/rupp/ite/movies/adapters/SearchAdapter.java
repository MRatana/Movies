package kh.edu.rupp.ite.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.model.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.ViewHolderSearchBinding;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Movie> searchResults;
    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position,View view);
    }

    private OnItemClickListener listen;

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
          Movie movieCard = searchResults.get(position);
          holder.bind(movieCard);

        // Set a click listener on the item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the position of the clicked item.
                int position = holder.getPosition();

                // Perform the desired action.
                if (listen != null) {
                    listen.onItemClick(movieCard, position, view);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listen = listener;
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

