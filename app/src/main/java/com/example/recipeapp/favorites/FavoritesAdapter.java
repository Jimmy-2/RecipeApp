/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.R;
import com.example.recipeapp.fragments.RecipeScreenFragment;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    Context context;
    ArrayList row_id, recipe_id, image_URL, title, summary, notes;

    public FavoritesAdapter(Context context, ArrayList row_id, ArrayList recipe_id, ArrayList image_URL, ArrayList title, ArrayList summary, ArrayList notes) {
        this.context = context;
        this.row_id = row_id ;
        this.recipe_id = recipe_id;
        this.image_URL = image_URL;
        this.title = title;
        this.summary = summary;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorites_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.tvRecipeTitle.setText(String.valueOf(title.get(position)));
        holder.tvRecipeSummary.setText(String.valueOf(summary.get(position)));
        holder.tvRecipeNotes.setText(String.valueOf(notes.get(position)));

        Glide.with(context).load(image_URL.get(position)).into(holder.ivRecipeImage);




        holder.favoritesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass recipe_id through bundle then transverse to Isaac's recipe screen

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                RecipeScreenFragment fragment = new RecipeScreenFragment();
                //using bundle to pass data to another fragment
                Bundle args = new Bundle();
                //key, value

                //pass the recipe's id to isaac's recipe screen so he can parse the api link with for the specific recipe
                args.putString("recipe_id", String.valueOf(recipe_id.get(position)));
                fragment.setArguments(args);
                //add a stack so we can click back button to go back to favorites screen
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).addToBackStack(null).commit();
            }
        });




    }

    @Override
    public int getItemCount() {
        return row_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivRecipeImage;
        TextView tvRecipeTitle, tvRecipeSummary, tvRecipeNotes;
        LinearLayout favoritesLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImageEdit);

            tvRecipeTitle = itemView.findViewById(R.id.tvRecipeTitleEdit);
            tvRecipeSummary= itemView.findViewById(R.id.tvRecipeSummary);
            tvRecipeNotes = itemView.findViewById(R.id.tvRecipeNotes);


            favoritesLayout = itemView.findViewById(R.id.favoritesLayout);




        }
    }


}
