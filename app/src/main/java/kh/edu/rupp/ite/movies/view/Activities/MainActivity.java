package kh.edu.rupp.ite.movies.view.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.databinding.ActivityMainBinding;
import kh.edu.rupp.ite.movies.view.fragment.AccountFragment;
import kh.edu.rupp.ite.movies.view.fragment.DownloadFragment;
import kh.edu.rupp.ite.movies.view.fragment.ExplorerFragment;
import kh.edu.rupp.ite.movies.view.fragment.HomeFragment;
import kh.edu.rupp.ite.movies.help.ShowFragment;
import kh.edu.rupp.ite.movies.view.FavoriteFragment2;


public class MainActivity extends AppCompatActivity{
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
                case R.id.favorite:
                    ShowFragment.show(new FavoriteFragment2(),getSupportFragmentManager(),R.id.lyFragment);
                    break;
                case R.id.download:
                    ShowFragment.show(new DownloadFragment(),getSupportFragmentManager(),R.id.lyFragment);
                    break;
                case R.id.explorer:
                    ShowFragment.show(new ExplorerFragment(),getSupportFragmentManager(),R.id.lyFragment);
                    break;
                case R.id.account:
                    ShowFragment.show(new AccountFragment(),getSupportFragmentManager(),R.id.lyFragment);
                    break;
                default:
                    ShowFragment.show(new HomeFragment(),getSupportFragmentManager(),R.id.lyFragment);
            }

            return true;
        });

    }
}