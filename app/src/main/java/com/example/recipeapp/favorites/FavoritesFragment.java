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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.recipeapp.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FavoritesDatabaseHelper favoritesDB;
    private ArrayList<String> row_id, recipe_id, image_URL, title, summary, notes;
    FavoritesAdapter favoritesAdapter;

    RecyclerView rvFavorites;
    private Spinner sortSpinner;

    private String sortingCol;
    private String sortingOrder;

    SortFavoritesDatabaseHelper sortDB;
    ArrayList<String> sortSettings;

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

        setSortSettings();

        sortSettings = new ArrayList<>();
        storeSortDataInArrays();

        sortSpinner = (Spinner) view.findViewById(R.id.sortSpinner);

        String[] sortItems = new String[] {};
        if(sortSettings.get(1).equals(String.valueOf(1))) {
            sortingCol = "_id";
            sortingOrder = "Asc";
            sortItems = new String[] { "Date\u2191", "Date\u2193", "Name\u2191", "Name\u2193"};
        }else if(sortSettings.get(2).equals(String.valueOf(1))) {
            sortingCol = "_id";
            sortingOrder = "Desc";
            sortItems = new String[] { "Date\u2193", "Date\u2191", "Name\u2191", "Name\u2193"};
        }else if(sortSettings.get(3).equals(String.valueOf(1))) {
            sortingCol = "title";
            sortingOrder = "Asc";
            sortItems = new String[] { "Name\u2191", "Name\u2193", "Date\u2191", "Date\u2193"};
        }else if(sortSettings.get(4).equals(String.valueOf(1))) {
            sortingCol = "title";
            sortingOrder = "Desc";
            sortItems = new String[] { "Name\u2193", "Name\u2191", "Date\u2191", "Date\u2193"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, sortItems);
        sortSpinner.setAdapter(adapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        checkSelection();
                        break;
                    case 2:
                        checkSelection();
                        break;
                    case 3:
                        checkSelection();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        return view;
    }

    void checkSelection() {
        if(sortSpinner.getSelectedItem().toString().equals("Date\u2191")) {
            sortDB.updateSortSetting("1","0","0","0");
        }else if(sortSpinner.getSelectedItem().toString().equals("Date\u2193")) {
            sortDB.updateSortSetting("0","1","0","0");
        }
        else if(sortSpinner.getSelectedItem().toString().equals("Name u2191")) {
            sortDB.updateSortSetting("0","0","1","0");
        }
        else if(sortSpinner.getSelectedItem().toString().equals("Name\u2193")) {
            sortDB.updateSortSetting("0","0","0","1");
        }

        getFragmentManager().beginTransaction().replace(R.id.flContainer, new FavoritesFragment()).commit();

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
                favoritesDB.addRecipe("644681", "www.image.jpg something", "Bacon Pie", "Summary: Bacon pie is the blah blah", "Notes: This is very good");
                favoritesDB.addRecipe("644681", "www.image.jpg something", "Steak Pie", "Summary: steak pie is the blah blah", "Notes: This is very ok");
                favoritesDB.addRecipe("716429", "www.image.jpg something", "Cheese Pie", "Summary: something new", "Notes: This is very bad");
                favoritesDB.addRecipe("716429", "www.image.jpg something", "Potato Pie", "Summary: another summary", "Notes: I cant make this");

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

    //check if app is opened for the first time and if it is, set the sorting setting
    void setSortSettings() {
        sortDB = new SortFavoritesDatabaseHelper(getActivity());
        if (sortDB.getSortCount() < 1) {
            sortDB.addSort(1, 0, 0, 0);
        }

    }

    void goToFavoritesEditFragment() {
        FavoritesEditFragment newFragment = new FavoritesEditFragment ();

        //add a stack so we can click back button to go back
        System.out.println("HELLO");
        getFragmentManager().beginTransaction().replace(R.id.flContainer, newFragment).addToBackStack(null).commit();
    }

    void storeSortDataInArrays() {
        Cursor cursor = sortDB.readSortSetting();
        if(cursor.getCount() == 0) {
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()) {
                sortSettings.add(cursor.getString(0));
                sortSettings.add(cursor.getString(1));
                sortSettings.add(cursor.getString(2));
                sortSettings.add(cursor.getString(3));
                sortSettings.add(cursor.getString(4));

            }
        }
    }


    void storeFavoritesDataInArrays() {
        Cursor cursor = favoritesDB.readAllDataSorted(sortingCol, sortingOrder);
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