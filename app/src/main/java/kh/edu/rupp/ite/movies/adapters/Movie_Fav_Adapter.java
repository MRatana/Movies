package kh.edu.rupp.ite.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.databinding.ViewHolderMoviesBinding;
import kh.edu.rupp.ite.movies.databinding.ViewHolderFavoriteMoviesBinding;
import kh.edu.rupp.ite.movies.databinding.ViewHolderExplorerMoviesBinding;

public class Movie_Fav_Adapter extends ListAdapter<Movie , RecyclerView.ViewHolder> {

    private static int displayMode;
    public static final int MODE_FAVORITE = 1;
    public static final int MODE_DETAIL = 2;
    public static final int MODE_EXPLORE = 3;

    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }
    private MovieAdapter.OnItemClickListener listener;
    public Movie_Fav_Adapter(int displayMode) {
        super(new DiffUtil.ItemCallback<Movie>() {
            @Override
            public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
        this.displayMode = displayMode;
    }

    @Override
    public int getItemViewType(int position) {
        // Return a view type based on the display mode
        return displayMode;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == MODE_FAVORITE) {
            ViewHolderFavoriteMoviesBinding binding = ViewHolderFavoriteMoviesBinding.inflate(inflater, parent, false);
            return new FavoriteViewHolder(binding);
        } else if (viewType == MODE_DETAIL) {
            ViewHolderMoviesBinding binding = ViewHolderMoviesBinding.inflate(inflater, parent, false);
            return new DetailViewHolder(binding);
        } else if (viewType == MODE_EXPLORE) {
            ViewHolderExplorerMoviesBinding binding = ViewHolderExplorerMoviesBinding.inflate(inflater, parent, false);
            return new ExploreViewHolder(binding);
        } else {
            // Handle other view types if needed
            return null;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie item = getItem(position);

        if (holder instanceof FavoriteViewHolder && displayMode == MODE_FAVORITE) {
            ((FavoriteViewHolder) holder).bind(item);
        } else if (holder instanceof DetailViewHolder && displayMode == MODE_DETAIL) {
            ((DetailViewHolder) holder).bind(item);
        } else if (holder instanceof ExploreViewHolder && displayMode == MODE_EXPLORE) {
            ((ExploreViewHolder) holder).bind(item);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the position of the clicked item.
                int position = holder.getAdapterPosition();

                // Perform the desired action.
                if (listener != null) {
                    listener.onItemClick(item, position);
                }
            }
        });
    }

    public void setOnItemClickListener(MovieAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ViewHolderFavoriteMoviesBinding itemBinding;

        public FavoriteViewHolder(ViewHolderFavoriteMoviesBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Movie movie) {
            Picasso.get().load(movie.getImg()).into(itemBinding.imgMovie);
            itemBinding.movieTitle.setText(movie.getTitle());
            itemBinding.movieCategory.setText(movie.getCategory());
        }
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        private ViewHolderMoviesBinding itemBinding;

        public DetailViewHolder(ViewHolderMoviesBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Movie movie) {
            Picasso.get().load(movie.getImg()).into(itemBinding.imgMovie);
            itemBinding.movieTitle.setText(movie.getTitle());
            itemBinding.movieCategory.setText(movie.getCategory());
            itemBinding.movieDescription.setText(movie.getDescription());
            itemBinding.movieRating.setText(movie.getRating());
        }
    }

    public class ExploreViewHolder extends RecyclerView.ViewHolder {
        private ViewHolderExplorerMoviesBinding itemBinding;

        public ExploreViewHolder(ViewHolderExplorerMoviesBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Movie movie) {
            Picasso.get().load(movie.getImg()).into(itemBinding.imgMovie);
            itemBinding.movieRating.setText(movie.getRating());
        }
    }

//    public static class MovieViewHolder extends RecyclerView.ViewHolder {
//        private ViewHolderMoviesBinding itemBinding;
//        public MovieViewHolder(ViewHolderMoviesBinding itemBinding) {
//            super(itemBinding.getRoot());
//            this.itemBinding = itemBinding;
//        }
//
//        public void bind(Movie movie){
//            Picasso.get().load(movie.getImg()).into(itemBinding.imgMovie);
//            itemBinding.movieTitle.setText(movie.getTitle());
//            itemBinding.movieCategory.setText(movie.getCategory());
//            itemBinding.movieDescription.setText(movie.getDescription());
//            itemBinding.movieRating.setText(movie.getRating());
//        }
//    }
}
