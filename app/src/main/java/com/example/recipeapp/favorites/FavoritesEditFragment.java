/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipeapp.R;

import java.util.ArrayList;


public class FavoritesEditFragment extends Fragment {

    private FavoritesDatabaseHelper favoritesDB;
    private ArrayList<String> row_id, recipe_id, image_URL, title, summary, notes;
    FavoritesEditAdapter favoritesEditAdapter;

    RecyclerView rvFavoritesEdit;

    Button btnDone;


    public FavoritesEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavoritesEdit = view.findViewById(R.id.rvFavoritesEdit);


        btnDone = view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();

                FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.replace(R.id.flContainer, new FavoritesFragment()).commit();

            }
        });

        favoritesDB = new FavoritesDatabaseHelper(getActivity());
        row_id = new ArrayList<>();
        recipe_id = new ArrayList<>();
        image_URL = new ArrayList<>();
        title = new ArrayList<>();
        summary = new ArrayList<>();
        notes = new ArrayList<>();

        storeFavoritesDataInArrays();

        favoritesEditAdapter = new FavoritesEditAdapter(getContext(), row_id, recipe_id, image_URL, title, summary, notes);
        rvFavoritesEdit.setAdapter(favoritesEditAdapter);
        rvFavoritesEdit.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    void storeFavoritesDataInArrays() {
        Cursor cursor = favoritesDB.readAllDataSorted("nothing", "nothing");
        if(cursor.getCount() == 0) {
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()) {
                row_id.add(cursor.getString(0));
                recipe_id.add(cursor.getString(1));
                image_URL.add(cursor.getString(2));
                title.add(cursor.getString(3));
                summary.add(cursor.getString(4));
                notes.add(cursor.getString(5));

            }
        }
    }

}