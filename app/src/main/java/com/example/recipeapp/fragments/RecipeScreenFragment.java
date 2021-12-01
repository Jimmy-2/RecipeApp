package com.example.recipeapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.recipeapp.R;
import com.example.recipeapp.favorites.FavoritesDatabaseHelper;
import com.example.recipeapp.favorites.FavoritesFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.Headers;

public class RecipeScreenFragment extends Fragment {

    //for api
    String recipe_id;


    private FavoritesDatabaseHelper favoritesDB;
    Button btnAddFav;

    String id;
    String recipeTitle;
    String imageURL;
    String servings;
    String readyTime;
    String sourceURL;
    String summary;
    String instructions;

    TextView tvTitle;

    public RecipeScreenFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_full, container, false);

        recipe_id = getArguments().getString("recipe_id");


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("HELLO"+recipe_id);
        tvTitle = view.findViewById(R.id.tvTitle);
        getRecipeData(recipe_id);






        btnAddFav = view.findViewById(R.id.btnAddFav);
        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoritesDB = new FavoritesDatabaseHelper(getActivity());
                favoritesDB.addRecipe(id, imageURL, recipeTitle, summary, "");

            }
        });


    }


    void getRecipeData(String recipe_id) {
        String TEST_API = "https://api.spoonacular.com/recipes/"+recipe_id+"/information?apiKey=f280b73ab3c948cfa40536f5e35735da&includeNutrition=false";
        System.out.println("HELLO"+TEST_API);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(TEST_API, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONObject results = jsonObject;
                    id = results.getString("id");
                    recipeTitle = results.getString("title");
                    imageURL = results.getString("image");
                    servings = results.getString("servings");
                    readyTime = results.getString("readyInMinutes");
                    sourceURL = results.getString("spoonacularSourceUrl");
                    summary = results.getString("summary");
                    instructions = results.getString("instructions");

                    tvTitle.setText(recipeTitle);


                    System.out.println("HELLO"+id);
                    System.out.println("HELLO"+recipeTitle);
                    System.out.println("HELLO"+imageURL);
                    System.out.println("HELLO"+servings);
                    System.out.println("HELLO"+readyTime);
                    System.out.println("HELLO"+sourceURL);
                    System.out.println("HELLO"+summary);
                    System.out.println("HELLO"+instructions);



                } catch (JSONException e) {
                    e.printStackTrace();



                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }



}