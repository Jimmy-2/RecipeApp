/**
 * Created by Jonathan.
 * */

package com.example.recipeapp.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapp.R;

import java.util.ArrayList;

public class RandomRecipeFragment extends Fragment {

//    RandomRecipeAdapter randomRecipeAdapter;

//    RecyclerView rvRandomRecipes;

    public RandomRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_randomrecipe, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRandomRecipes= view.findViewById(R.id.rvRandomRecipes);

    }


}