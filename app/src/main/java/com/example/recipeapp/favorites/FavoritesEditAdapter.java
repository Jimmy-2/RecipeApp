/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;

import java.util.ArrayList;

public class FavoritesEditAdapter extends RecyclerView.Adapter<FavoritesEditAdapter.MyViewHolder> {

    Context context;
    ArrayList row_id, recipe_id, image_URL, title, summary, notes;

    public FavoritesEditAdapter(Context context, ArrayList row_id, ArrayList recipe_id, ArrayList image_URL, ArrayList title, ArrayList summary, ArrayList notes) {
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
    public FavoritesEditAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorites_edit_row, parent, false);
        return new FavoritesEditAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesEditAdapter.MyViewHolder holder, int position) {

        holder.tvRecipeTitle.setText(String.valueOf(title.get(position)));



        holder.favoritesEditLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit notes here or do nothing and instead use another onclick for the edit notes button in MyViewHolder class
            }
        });


    }

    @Override
    public int getItemCount() {
        return row_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvRecipeTitle;
        LinearLayout favoritesEditLayout;

        ImageView ivRecipeImage, ivDeleteRecipe;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecipeTitle = itemView.findViewById(R.id.tvRecipeTitle);

            favoritesEditLayout = itemView.findViewById(R.id.favoritesEditLayout);

            ivDeleteRecipe = itemView.findViewById(R.id.ivDeleteRecipe);

            //click on the ivDeleteRecipe imageview to delete current recipe from database.
            ivDeleteRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        //testing position
                        //Toast.makeText(context, "delete"+position,  Toast.LENGTH_SHORT).show();


                        FavoritesDatabaseHelper favoritesDB = new FavoritesDatabaseHelper(ivDeleteRecipe.getContext());
                        favoritesDB.deleteRecipe(String.valueOf(row_id.get(position)));

                    }

                    //refresh recyclerview after deleting recipe from database here:
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    FavoritesEditFragment fragment = new FavoritesEditFragment();



                    //refresh the fragment when you delete a recyclerview/database item
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();




                }
            });




        }
    }
}
