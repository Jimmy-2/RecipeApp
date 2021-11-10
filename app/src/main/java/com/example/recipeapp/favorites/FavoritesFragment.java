/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipeapp.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FavoritesDatabaseHelper favoritesDB;
    private ArrayList<String> row_id, recipe_id, image_URL, title, summary, notes;
    FavoritesAdapter favoritesAdapter;

    RecyclerView rvFavorites;

    Button btnGoToEdit, btnTest;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorites, container, false);

        //other code here, adding later

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorites= view.findViewById(R.id.rvFavorites);

        btnGoToEdit = view.findViewById(R.id.btnGoToEdit);
        btnGoToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFavoritesEditFragment();
            }
        });

        btnTest = view.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add fake favorites recipe data here

                favoritesDB = new FavoritesDatabaseHelper(getActivity());
                favoritesDB.addRecipe(132321, "www.image.jpg something", "Bacon Pie", "Summary: Bacon pie is the blah blah", "Notes: This is very good");
                favoritesDB.addRecipe(132321, "www.image.jpg something", "Steak Pie", "Summary: steak pie is the blah blah", "Notes: This is very ok");
                favoritesDB.addRecipe(132321, "www.image.jpg something", "Cheese Pie", "Summary: something new", "Notes: This is very bad");
                favoritesDB.addRecipe(132321, "www.image.jpg something", "Potato Pie", "Summary: another summary", "Notes: I cant make this");

                getFragmentManager().beginTransaction().replace(R.id.flContainer, new FavoritesFragment()).commit();

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

        favoritesAdapter = new FavoritesAdapter(getContext(), row_id, recipe_id, image_URL, title, summary, notes);
        rvFavorites.setAdapter(favoritesAdapter);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    void goToFavoritesEditFragment() {
        FavoritesEditFragment newFragment = new FavoritesEditFragment ();
        //add a stack so we can click back button to go back
        getFragmentManager().beginTransaction().replace(R.id.flContainer, newFragment).addToBackStack(null).commit();
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