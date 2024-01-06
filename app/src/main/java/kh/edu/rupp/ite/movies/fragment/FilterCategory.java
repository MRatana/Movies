package kh.edu.rupp.ite.movies.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kh.edu.rupp.ite.movies.databinding.FilterCategoryBinding;

public class FilterCategory extends Fragment {

    private FilterCategoryBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FilterCategoryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


}
