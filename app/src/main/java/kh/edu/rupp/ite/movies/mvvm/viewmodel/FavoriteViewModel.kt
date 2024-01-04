package kh.edu.rupp.ite.movies.mvvm.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.movies.api.client.ApiClient
import kh.edu.rupp.ite.movies.api.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _moviesList

    fun loadListMovieFromServer() {
        val tasks: Call<List<Movie>> = ApiClient.get().apiService.loadMoviesList()

        tasks.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful) {
                    _moviesList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}