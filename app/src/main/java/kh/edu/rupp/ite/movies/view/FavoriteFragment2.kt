package kh.edu.rupp.ite.movies.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.movies.view.Activities.MovieDetailActivity
import kh.edu.rupp.ite.movies.adapters.Movie_Fav_Adapter
import kh.edu.rupp.ite.movies.model.api.model.Movie
import kh.edu.rupp.ite.movies.databinding.FragmentFavoriteBinding
import kh.edu.rupp.ite.movies.viewmodel.FavoriteViewModel

class FavoriteFragment2 : Fragment(), Movie_Fav_Adapter.OnItemClickListener {

    private lateinit var viewModel: FavoriteViewModel
    private var binding: FragmentFavoriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        val recyclerView: RecyclerView = binding!!.recyclerView
        recyclerView.layoutManager = layoutManager

        val adapter = Movie_Fav_Adapter(Movie_Fav_Adapter.MODE_FAVORITE)
        adapter.setOnItemClickListener(this@FavoriteFragment2::onItemClick)
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.moviesList.observe(viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                (binding?.recyclerView?.adapter as? Movie_Fav_Adapter)?.submitList(it)
            }
        })

        viewModel.loadListMovieFromServer()
    }

    override fun onItemClick(movie: Movie, position: Int) {
        val array = arrayOf(
            movie.id,
            movie.title,
            movie.description,
            movie.img,
            movie.video,
            movie.rating
        )
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("movie", array)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}