package kh.edu.rupp.ite.movies.view.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kh.edu.rupp.ite.movies.databinding.ActivitySignupBinding;
import kh.edu.rupp.ite.movies.view.LoginActivity2;

public class SignupActivity extends AppCompatActivity {

    public ActivitySignupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnSignup.setOnClickListener( v -> openMainActivity());

        binding.txtLogin.setOnClickListener( v -> openLoginActivity());
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity2.class);
        startActivity(intent);
    }
}
