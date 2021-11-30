package com.example.recipeapp.recipeFull;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.recipeapp.R;
import com.example.recipeapp.fragments.TestFragment;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;
import com.bumptech.glide.Glide;

public class RecipeFullFragment extends Fragment {

    TextView tvTitle;
    TextView tvReadyMin;
    TextView tvServings;
    ImageView ivFood;
    String recipeID;
    public static final String TAG = "MainActivity";

    //geView
    public RecipeFullFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_test, container, false);
        View view = inflater.inflate(R.layout.fragment_recipe_full, container, false);
        recipeID = getArguments().getString("recipe_id");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getRecipeData(recipeID);
        tvTitle = view.findViewById(R.id.tvTitle);
        ivFood = view.findViewById(R.id.ivFood);
        tvReadyMin = view.findViewById(R.id.tvReadyMin);
        tvServings = view.findViewById(R.id.tvServings);
    }

    void getRecipeData(String recipeID) {
        String TEST_API = "https://api.spoonacular.com/recipes/" + recipeID + "/information?apiKey=YOUR-API-KEY&includeNutrition=false";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(TEST_API, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONObject results = jsonObject;
                    String id = results.getString("id");
                    String imageURL = results.getString("image");
                    String servings = results.getString("servings");
                    String readyTime = results.getString("readyInMinutes");
                    String sourceURL = results.getString("spoonacularSourceUrl");

                    tvTitle.setText(id);
                    tvReadyMin.setText("Ready in " + readyTime + "minutes.");
                    tvServings.setText("Makes " + servings + " servings");
                    Glide.with(getContext()).load(imageURL).into(ivFood);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }
}