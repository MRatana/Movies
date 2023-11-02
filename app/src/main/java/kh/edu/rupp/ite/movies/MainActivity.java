package kh.edu.rupp.ite.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kh.edu.rupp.ite.movies.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_favorite);
    }
}