package kh.edu.rupp.ite.movies.api.service;

import java.util.List;

import kh.edu.rupp.ite.movies.api.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/MRatana/movieapi/main/movies.json")
    Call<List<Movie>> getMovie();
}
