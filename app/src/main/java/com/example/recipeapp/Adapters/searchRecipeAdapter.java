package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;

import java.util.ArrayList;

public class searchRecipeAdapter extends RecyclerView.Adapter<searchRecipeAdapter.ViewHolder> {
    public Context context;
    ArrayList row_id, recipe_id, image_URL, title, summary, notes;

    public searchRecipeAdapter(Context context, ArrayList row_id, ArrayList recipe_id, ArrayList image_URL, ArrayList title, ArrayList summary, ArrayList notes) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_searchrecipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRecipeTitle.setText(String.valueOf(title.get(position)));
        holder.tvRecipeSummary.setText(String.valueOf(summary.get(position)));
        holder.tvRecipeNotes.setText(String.valueOf(notes.get(position)));



        holder.recipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass recipe_id through bundle then transverse to Isaac's recipe screen
            }
        });

    }

    @Override
    public int getItemCount() {
        return row_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivRecipeImage;
        TextView tvRecipeTitle, tvRecipeSummary, tvRecipeNotes;
        LinearLayout recipeLayout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImageEdit);

            tvRecipeTitle = itemView.findViewById(R.id.tvRecipeTitleEdit);
            tvRecipeSummary= itemView.findViewById(R.id.tvRecipeSummary);
            tvRecipeNotes = itemView.findViewById(R.id.tvRecipeNotes);


            recipeLayout = itemView.findViewById(R.id.recipeLayout);




        }
    }


} // end of class
