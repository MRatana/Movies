package kh.edu.rupp.ite.movies.api.service;

import java.util.List;

import kh.edu.rupp.ite.movies.api.model.Movie;
import kh.edu.rupp.ite.movies.mvvm.model.Movie2;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/MRatana/movieapi/main/movies.json")
    Call<List<Movie>>loadMoviesList();

    @GET("/MRatana/movieapi/main/profile.json")
    Call<kh.edu.rupp.ite.movies.api.model.Profile> loadProfile();

}
