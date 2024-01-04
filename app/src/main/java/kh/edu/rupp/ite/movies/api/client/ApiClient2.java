package kh.edu.rupp.ite.movies.api.client;

import kh.edu.rupp.ite.movies.BuildConfig;
import kh.edu.rupp.ite.movies.api.service.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {

    private final Retrofit httpClient;
    private final ApiService apiService;
    private static ApiClient2 instance;

    private ApiClient2() {
        httpClient = new Retrofit.Builder()
                .baseUrl(BuildConfig.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = httpClient.create(ApiService.class);
    }

    public static synchronized ApiClient2 get() {
        if (instance == null) {
            instance = new ApiClient2();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
