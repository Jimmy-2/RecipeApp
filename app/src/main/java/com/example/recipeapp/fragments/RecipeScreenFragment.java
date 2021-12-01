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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
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
    TextView tvReadyMin;
    TextView tvServings;
    TextView tvInstructions;
    TextView tvSummary;
    ImageView ivFood;

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

        ivFood = view.findViewById(R.id.ivFood);
        tvReadyMin = view.findViewById(R.id.tvReadyMin);
        tvServings = view.findViewById(R.id.tvServings);
        tvSummary = view.findViewById(R.id.tvSummary);
        tvInstructions = view.findViewById(R.id.tvInstructions);

        getRecipeData("661494");


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
                    summary = removeTag(results.getString("summary"));
                    instructions = removeTag(results.getString("instructions"));

                    tvTitle.setText(recipeTitle);

                    tvReadyMin.setText("Ready in " + readyTime + " minutes.");
                    tvServings.setText("Makes " + servings + " servings");
                    tvInstructions.setText(instructions);
                    tvSummary.setText(summary);
                    Glide.with(getContext()).load(imageURL).into(ivFood);
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

    String removeTag(String tagged){
        String untagged;
        String tag = "</b>";
        untagged = tagged.replaceAll(tag," ");
        String tag1 = "<b>";
        untagged=untagged.replaceAll(tag1," ");
        String tag2 = "<a>";
        untagged=untagged.replaceAll(tag2," ");
        String tag4 = "</a>";
        untagged=untagged.replaceAll(tag4," ");
        String tag3 = "<li>";
        untagged=untagged.replaceAll(tag3," ");
        String tag5 = "</li>";
        untagged=untagged.replaceAll(tag5," ");
        String tag6 = "<ol>";
        untagged=untagged.replaceAll(tag6," ");
        String tag7 = "</ol>";
        untagged=untagged.replaceAll(tag7," ");
        return untagged;
    }

}