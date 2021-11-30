/**
 * Created by Jonathan.
 * */
/*
package com.example.recipeapp.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.R;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.ViewHolder> {
    private Context context;
    private List<RecipeCard> recipe;

    public RandomRecipeAdapter(Context context, List<RecipeCard> posts) {
        this.context = context;
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout., parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeAdapter.ViewHolder holder, int position) {
        RecipeCard post = recipe.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return recipe.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        recipe.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<RecipeCard> list) {
        recipe.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRecipeTitle;
        private ImageView ivRecipeImage;
        private TextView tvRecipeSummary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipeTitle = itemView.findViewById(R.id.tvRecipeTitle);
            ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);
            tvRecipeSummary = itemView.findViewById(R.id.tvRecipeSummary);
        }

        public void bind(RecipeCard card) {
            // Bind the post data to the view elements
            tvRecipeSummary.setText(card.);
            tvRecipeTitle.setText(card.);
            ParseFile image = card.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivRecipeImage);
            }
        }
    }
}

}
*/