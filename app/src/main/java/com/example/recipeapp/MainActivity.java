package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recipeapp.favorites.FavoritesFragment;
import com.example.recipeapp.fragments.TestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_change1:
                        Toast.makeText(MainActivity.this, "test1", Toast.LENGTH_SHORT).show();
                        fragment = new TestFragment();
                        break;
                    case R.id.action_change2:
                        Toast.makeText(MainActivity.this, "test2", Toast.LENGTH_SHORT).show();

                        fragment = new TestFragment();

                        break;

                    case R.id.action_change3:
                        Toast.makeText(MainActivity.this, "test3", Toast.LENGTH_SHORT).show();

                        fragment = new TestFragment();

                        break;
                    case R.id.action_change4:
                    default:
                        Toast.makeText(MainActivity.this, "test4", Toast.LENGTH_SHORT).show();
                        fragment = new FavoritesFragment();

                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }

        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_change1);
    }

    // pop/remove stack once we have successfully gone back to previous fragment. This is only used if you decide to use addToBackStack when transversing fragments
    public void onBackPressed()
    {
        FragmentManager fm = MainActivity.this.getSupportFragmentManager();
        fm.popBackStack();
    }
}