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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.Headers;

public class RecipeScreenFragment extends Fragment {

    //for api
    String recipe_id;


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
        View view = inflater.inflate(R.layout.fragment_recipe_screen, container, false);

        recipe_id = getArguments().getString("recipe_id");


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("HELLO"+recipe_id);
        getRecipeData(recipe_id);


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
                    String id = results.getString("id");
                    String recipeTitle = results.getString("title");
                    String imageURL = results.getString("image");
                    String servings = results.getString("servings");
                    String readyTime = results.getString("readyInMinutes");
                    String sourceURL = results.getString("spoonacularSourceUrl");
                    String summary = results.getString("summary");
                    String instructions = results.getString("instructions");

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