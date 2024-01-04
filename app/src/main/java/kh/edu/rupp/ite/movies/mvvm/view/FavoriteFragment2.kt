package kh.edu.rupp.ite.movies.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kh.edu.rupp.ite.movies.Activities.MovieDetail
import kh.edu.rupp.ite.movies.adapters.Movie_Fav_Adapter
import kh.edu.rupp.ite.movies.api.model.Movie
import kh.edu.rupp.ite.movies.databinding.FragmentFavoriteBinding
import kh.edu.rupp.ite.movies.mvvm.viewmodel.FavoriteViewModel

class FavoriteFragment2 : Fragment(), Movie_Fav_Adapter.OnItemClickListener {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        observeViewModel()
        viewModel.loadListMovieFromServer()
    }

    private fun observeViewModel() {
        viewModel.moviesList.observe(viewLifecycleOwner, this::showMovieList)
    }

    private fun showMovieList(moviesList: List<Movie>) {
        val layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = layoutManager

        val adapter = Movie_Fav_Adapter(Movie_Fav_Adapter.MODE_FAVORITE)
        adapter.setOnItemClickListener { movie, position -> onItemClick(movie, position) }
        adapter.submitList(moviesList)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(movie: Movie, position: Int) {
        val array = arrayOf(movie.id, movie.title, movie.description, movie.img, movie.rating)
        val intent = Intent(requireContext(), MovieDetail::class.java)
        intent.putExtra("movie", array)
        startActivity(intent)
    }
}