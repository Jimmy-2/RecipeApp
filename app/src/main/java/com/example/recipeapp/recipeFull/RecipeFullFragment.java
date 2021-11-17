package com.example.recipeapp.recipeFull;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapp.R;
import com.example.recipeapp.fragments.TestFragment;

public class RecipeFullFragment extends Fragment {

    public RecipeFullFragment() {
        // Required empty public constructor
    }

   /* public static com.example.recipeapp.fragments.TestFragment newInstance(String param1, String param2) {
        TestFragment f = TestFragment.newInstance();
        return f;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }
}