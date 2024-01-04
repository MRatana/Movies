package kh.edu.rupp.ite.movies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.Activities.EditProfileActivity;
import kh.edu.rupp.ite.movies.api.client.ApiClient;
import kh.edu.rupp.ite.movies.api.model.Profile;
import kh.edu.rupp.ite.movies.databinding.FragmentAccountBinding;
import kh.edu.rupp.ite.movies.mvvm.view.LoginActivity2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {

    public FragmentAccountBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadProfileFromServer();

        binding.profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

    }

    private void openActivity(){
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }
    private void openLoginActivity(){
        Intent intent = new Intent(getActivity(), LoginActivity2.class);
        startActivity(intent);
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
    private void showProfile(Profile profile){

        Picasso.get().load(profile.getImgProfile()).into(binding.imgProfile);
        binding.name.setText(profile.getName());
        binding.email.setText(profile.getEmail());

    }
}
