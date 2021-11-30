/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

        holder.tvRecipeTitleEdit.setText(String.valueOf(title.get(position)));
        holder.tvRecipeSummaryEdit.setText(String.valueOf(summary.get(position)));

        holder.etRecipeNotesEdit.setText(String.valueOf(notes.get(position)));
        holder.etRecipeNotesEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        holder.etRecipeNotesEdit.setRawInputType(InputType.TYPE_CLASS_TEXT);

        Glide.with(context).load(image_URL.get(position)).into(holder.ivRecipeImage);


        holder.favoritesEditLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the recipe screen
            }
        });


    }

    @Override
    public int getItemCount() {
        return row_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvRecipeTitleEdit, tvRecipeSummaryEdit;
        EditText etRecipeNotesEdit;
        LinearLayout favoritesEditLayout;
        Button btnClearNotes;


        ImageView ivRecipeImage, ivDeleteRecipe;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecipeTitleEdit = itemView.findViewById(R.id.tvRecipeTitleEdit);
            tvRecipeSummaryEdit = itemView.findViewById(R.id.tvRecipeSummaryEdit );
            etRecipeNotesEdit = itemView.findViewById(R.id.etRecipeNotesEdit);

            favoritesEditLayout = itemView.findViewById(R.id.favoritesEditLayout);

            ivDeleteRecipe = itemView.findViewById(R.id.ivDeleteRecipe);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImageEdit);

            btnClearNotes = itemView.findViewById(R.id.btnClearNotes);
            btnClearNotes.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        FavoritesDatabaseHelper favoritesDB = new FavoritesDatabaseHelper(ivDeleteRecipe.getContext());
                        favoritesDB.updateNotes(String.valueOf(row_id.get(position)), "");

                    }

                    //refresh recyclerview after deleting recipe from database here:
                    AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                    FavoritesEditFragment fragment = new FavoritesEditFragment();

                    FragmentManager fm = activity.getSupportFragmentManager();
                    fm.popBackStack();

                    //refresh the fragment when you delete a recyclerview/database item
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).addToBackStack(null).commit();
                }
            });

            etRecipeNotesEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            String newNotes = etRecipeNotesEdit.getText().toString();

                            //testing position
                            //Toast.makeText(context, "delete"+position,  Toast.LENGTH_SHORT).show();


                            FavoritesDatabaseHelper favoritesDB = new FavoritesDatabaseHelper(ivDeleteRecipe.getContext());
                            favoritesDB.updateNotes(String.valueOf(row_id.get(position)), newNotes);

                        }

                        //refresh recyclerview after deleting recipe from database here:
                        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                        FavoritesEditFragment fragment = new FavoritesEditFragment();

                        FragmentManager fm = activity.getSupportFragmentManager();
                        fm.popBackStack();

                        //refresh the fragment when you delete a recyclerview/database item
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).addToBackStack(null).commit();
                    }
                    return false;
                }
            });


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

                    FragmentManager fm = activity.getSupportFragmentManager();
                    fm.popBackStack();

                    FavoritesEditFragment fragment = new FavoritesEditFragment();



                    //refresh the fragment when you delete a recyclerview/database item
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).addToBackStack(null).commit();




                }
            });




        }
    }
}
