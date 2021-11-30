package com.example.recipeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import androidx.recyclerview.widget.RecyclerView;

public class searchRecipeActivity extends Activity {


    private static final String TAG = "searchRecipeActivity";
    private RecyclerView recycleV;
    private adapter ArrayAdapter<>;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchrecipe);

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countries_array))
        lv_listView.adapter = adapter;
        lv_listViwe.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id =>
            Toast.makeText(applicationContext, parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show())
        }
        lv_listView.emptyView = tv_emptyTextView;

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu);

        val search = menu ?.findItem(R.id.nav_search);
        val searchView = search ?.ctionView as SearchView
        searchView.queryHint = "search"

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String ?):Boolean {
                return false;
            }

            override fun onQueryTextChange(newText tring ?):Boolean {
                adapteeer.filter.filter(newText);
                return true;
            }
        })
        return super.onCreateOptionsMenu(menu);
    }

    // recycleV = findViewById(R.id.recycleV);

    // recycleV.setOnClickListener((view)->{

    // });


    // goto recipe screen after clicking a searched recipe
    private void goRecipeScreen() {
        Log.d(TAG, "Navigate to recipe screen");
        // Intent i = new Intent(this, recipeScreen.class);
        // startActivity(i);
        finish();
    }
} // end of public class
