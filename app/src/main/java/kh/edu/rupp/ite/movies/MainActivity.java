package kh.edu.rupp.ite.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import kh.edu.rupp.ite.movies.databinding.ActivityMainBinding;
import kh.edu.rupp.ite.movies.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            return true;
        });
    }



    private void showFragment(Fragment fragment){
        // FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace fragment in lytFragment
        fragmentTransaction.replace(R.id.lyFragment, fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }
}