package kh.edu.rupp.ite.movies.model.api.service;

import java.util.List;

import kh.edu.rupp.ite.movies.model.api.model.Movie;
import kh.edu.rupp.ite.movies.model.api.model.Profile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/MRatana/movieapi/main/movies.json")
    Call<List<Movie>>loadMoviesList();

    @GET("/MRatana/movieapi/main/profile.json")
    Call<Profile> loadProfile();

    @GET("/MRatana/movieapi/main/movies.json")
    Call<List<Movie>> searchMovies(@Query("query") String query);

}
