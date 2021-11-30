package com.example.recipeapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recipeapp.Adapters.searchRecipeAdapter;
import com.example.recipeapp.R;

import java.util.ArrayList;

public class searchRecipeFragment extends Fragment {
    private final String TAG = "searchRecipeFragment";
    private RecyclerView recycleV;
    private TextView searchRecipeLabel;
    protected searchRecipeAdapter adapter;
    // protected List<Post> mPosts;

    // onCreateView to inflate the view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_searchrecipe, container, false);
        return view;
    } // end of onCreate

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recycleV = view.findViewById(R.id.recycleV);

        // 1. Create the data source
        // mPosts = new ArrayList<>();

        // 2. Create the adapter
        // adapter = new searchRecipeAdapter(getContext(), mPosts);

        // 3. Set the adapter on the recycler view
        recycleV.setAdapter(adapter);

        // 4. Set the layout manager on the recycler view
        recycleV.setLayoutManager(new LinearLayoutManager(getContext()));

    } // end of onViewCreated




}
