package kh.edu.rupp.ite.movies.model.api.service;

import java.util.List;

import kh.edu.rupp.ite.movies.model.api.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/MRatana/movieapi/main/movies.json")
    Call<List<Movie>> getMovie();
}
