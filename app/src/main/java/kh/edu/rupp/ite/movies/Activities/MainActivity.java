package kh.edu.rupp.ite.movies.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.databinding.ActivityMainBinding;
import kh.edu.rupp.ite.movies.fragment.FilterCategory;
import kh.edu.rupp.ite.movies.fragment.HomeFragment;
import kh.edu.rupp.ite.movies.help.ShowFragment;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ShowFragment.show(new HomeFragment(),getSupportFragmentManager(),R.id.lyFragment);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_home:
                    ShowFragment.show(new HomeFragment(),getSupportFragmentManager(),R.id.lyFragment);
                    break;
                default:
                    ShowFragment.show(new FilterCategory(),getSupportFragmentManager(),R.id.lyFragment);

            }

            return true;
        });
    }


}