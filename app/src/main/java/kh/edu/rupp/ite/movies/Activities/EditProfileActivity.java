package kh.edu.rupp.ite.movies.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.api.client.ApiClient;
import kh.edu.rupp.ite.movies.api.model.Profile;
import kh.edu.rupp.ite.movies.api.service.ApiService;
import kh.edu.rupp.ite.movies.databinding.ActivityProfileEditBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {

    public ActivityProfileEditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadProfileFromServer();

        binding.backAccount.setOnClickListener( v -> {
            finish();
        });

    }
    private void loadProfileFromServer(){

        Call<Profile> tasks = ApiClient.get().getApiService().loadProfile();

        tasks.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    showProfile(response.body());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }
    private String name;
    private String email;
    private void showProfile(Profile profile){

        name = profile.getName();
        email = profile.getEmail();

        Picasso.get().load(profile.getImgProfile()).into(binding.imgProfile);
        binding.name.setText(name);
        binding.email.setText(email);

        binding.editName.setText(name);
        binding.editEmail.setText(email);

    }
}
