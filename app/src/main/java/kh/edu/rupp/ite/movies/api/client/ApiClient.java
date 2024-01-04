package kh.edu.rupp.ite.movies.api.client;

import kh.edu.rupp.ite.movies.BuildConfig;
import kh.edu.rupp.ite.movies.api.service.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private final Retrofit httpClient;
    private final ApiService apiService;
    private static ApiClient instance;

    private ApiClient() {
        httpClient = new Retrofit.Builder()
                .baseUrl(BuildConfig.apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = httpClient.create(ApiService.class);
    }

    public static synchronized ApiClient get() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
