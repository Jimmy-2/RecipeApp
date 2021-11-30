/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.recipeapp.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FavoritesDatabaseHelper favoritesDB;
    private ArrayList<String> row_id, recipe_id, image_URL, title, summary, notes;
    FavoritesAdapter favoritesAdapter;

    RecyclerView rvFavorites;
    private Spinner sortSpinner;

    private String sortingCol;
    private String sortingOrder;

    SortFavoritesDatabaseHelper sortDB;
    ArrayList<String> sortSettings;

    Button btnGoToEdit;

    ImageView invisibleTextButton;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favorites, container, false);

        //other code here, adding later

        setSortSettings();

        sortSettings = new ArrayList<>();
        storeSortDataInArrays();

        sortSpinner = (Spinner) view.findViewById(R.id.sortSpinner);

        String[] sortItems = new String[] {};
        if(sortSettings.get(1).equals(String.valueOf(1))) {
            sortingCol = "_id";
            sortingOrder = "Asc";
            sortItems = new String[] { "Date\u2191", "Date\u2193", "Name\u2191", "Name\u2193"};
        }else if(sortSettings.get(2).equals(String.valueOf(1))) {
            sortingCol = "_id";
            sortingOrder = "Desc";
            sortItems = new String[] { "Date\u2193", "Date\u2191", "Name\u2191", "Name\u2193"};
        }else if(sortSettings.get(3).equals(String.valueOf(1))) {
            sortingCol = "title";
            sortingOrder = "Asc";
            sortItems = new String[] { "Name\u2191", "Name\u2193", "Date\u2191", "Date\u2193"};
        }else if(sortSettings.get(4).equals(String.valueOf(1))) {
            sortingCol = "title";
            sortingOrder = "Desc";
            sortItems = new String[] { "Name\u2193", "Name\u2191", "Date\u2191", "Date\u2193"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, sortItems);
        sortSpinner.setAdapter(adapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        checkSelection();
                        break;
                    case 2:
                        checkSelection();
                        break;
                    case 3:
                        checkSelection();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        return view;
    }

    void checkSelection() {
        if(sortSpinner.getSelectedItem().toString().equals("Date\u2191")) {
            sortDB.updateSortSetting("1","0","0","0");
        }else if(sortSpinner.getSelectedItem().toString().equals("Date\u2193")) {
            sortDB.updateSortSetting("0","1","0","0");
        }
        else if(sortSpinner.getSelectedItem().toString().equals("Name\u2191")) {
            sortDB.updateSortSetting("0","0","1","0");
        }
        else if(sortSpinner.getSelectedItem().toString().equals("Name\u2193")) {
            sortDB.updateSortSetting("0","0","0","1");
        }

        getFragmentManager().beginTransaction().replace(R.id.flContainer, new FavoritesFragment()).commit();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorites= view.findViewById(R.id.rvFavorites);

        invisibleTextButton = view.findViewById(R.id.invisibleTextButton);
        invisibleTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoritesDB = new FavoritesDatabaseHelper(getActivity());
                favoritesDB.addRecipe("716429", "https://spoonacular.com/recipeImages/716429-556x370.jpg", "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs", "Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs might be just the main course you are searching for. This recipe makes 2 servings with <b>636 calories</b>, <b>21g of protein</b>, and <b>20g of fat</b> each. For <b>$1.83 per serving</b>, this recipe <b>covers 24%</b> of your daily requirements of vitamins and minerals. From preparation to the plate, this recipe takes about <b>45 minutes</b>. This recipe is liked by 209 foodies and cooks. If you have pasta, salt and pepper, cheese, and a few other ingredients on hand, you can make it. To use up the extra virgin olive oil you could follow this main course with the <a href=\\\"https://spoonacular.com/recipes/peach-crisp-healthy-crisp-for-breakfast-487698\\\">Peach Crisp: Healthy Crisp for Breakfast</a> as a dessert. All things considered, we decided this recipe <b>deserves a spoonacular score of 86%</b>. This score is tremendous. Try <a href=\\\"https://spoonacular.com/recipes/cauliflower-gratin-with-garlic-breadcrumbs-318375\\\">Cauliflower Gratin with Garlic Breadcrumbs</a>, <a href=\\\"https://spoonacular.com/recipes/pasta-with-cauliflower-sausage-breadcrumbs-30437\\\">Pasta With Cauliflower, Sausage, & Breadcrumbs</a>, and <a href=\\\"https://spoonacular.com/recipes/pasta-with-roasted-cauliflower-parsley-and-breadcrumbs-30738\\\">Pasta With Roasted Cauliflower, Parsley, And Breadcrumbs</a> for similar recipes.", "");
                favoritesDB.addRecipe("644488", "https://spoonacular.com/recipeImages/644488-556x370.jpg", "German Rhubarb Cake with Meringue", "The recipe German Rhubarb Cake with Meringue could satisfy your European craving in around <b>around 45 minutes</b>. For <b>61 cents per serving</b>, you get a dessert that serves 12. One portion of this dish contains approximately <b>5g of protein</b>, <b>4g of fat</b>, and a total of <b>212 calories</b>. 15 people were glad they tried this recipe. A mixture of rhubarb, sugar, eggs, and a handful of other ingredients are all it takes to make this recipe so tasty. It is brought to you by Foodista. It will be a hit at your <b>Mother's Day</b> event. It is a good option if you're following a <b>dairy free and lacto ovo vegetarian</b> diet. With a spoonacular <b>score of 33%</b>, this dish is rather bad. Try <a href=\\\"https://spoonacular.com/recipes/rhubarb-meringue-cake-381275\\\">Rhubarb Meringue Cake</a>, <a href=\\\"https://spoonacular.com/recipes/rhubarb-meringue-pie-134891\\\">Rhubarb Meringue Pie</a>, and <a href=\\\"https://spoonacular.com/recipes/rhubarb-meringue-pie-387893\\\">Rhubarb Meringue Pie</a> for similar recipes.", "");
                favoritesDB.addRecipe("653445", "https://spoonacular.com/recipeImages/653445-556x370.jpg", "Oatmeal Coconut Cookies", "You can never have too many dessert recipes, so give Oatmeal Coconut Cookies a try. This lacto ovo vegetarian recipe serves 32 and costs <b>7 cents per serving</b>. One serving contains <b>78 calories</b>, <b>1g of protein</b>, and <b>4g of fat</b>. 8 people were glad they tried this recipe. Head to the store and pick up rolled oats, caster sugar, baking soda, and a few other things to make it today. It is brought to you by Foodista. From preparation to the plate, this recipe takes approximately <b>approximately 45 minutes</b>. Overall, this recipe earns a <b>very bad (but still fixable) spoonacular score of 4%</b>. Similar recipes are <a href=\"https://spoonacular.com/recipes/dark-chocolate-raspberry-coconut-oatmeal-cookies-made-with-coconut-oil-809865\">Dark Chocolate Raspberry Coconut Oatmeal Cookies (made with coconut oil!)</a>, <a href=\"https://spoonacular.com/recipes/coconut-oatmeal-cookies-719474\">Coconut Oatmeal Cookies</a>, and <a href=\"https://spoonacular.com/recipes/oatmeal-coconut-cookies-247148\">Oatmeal Coconut Cookies</a>", "");
                favoritesDB.addRecipe("632570", "https://spoonacular.com/recipeImages/632570-556x370.jpg", "Apple Pie Honey Wheat Scones", "Apple Pie Honey Wheat Scones is an American breakfast. One serving contains <b>304 calories</b>, <b>5g of protein</b>, and <b>15g of fat</b>. This lacto ovo vegetarian recipe serves 8 and costs <b>49 cents per serving</b>. 8 people found this recipe to be yummy and satisfying. A mixture of heavy cream, salt, brandy, and a handful of other ingredients are all it takes to make this recipe so tasty. It is brought to you by Foodista. From preparation to the plate, this recipe takes roughly <b>roughly 45 minutes</b>. Taking all factors into account, this recipe <b>earns a spoonacular score of 27%</b>, which is not so tremendous", "");
                favoritesDB.addRecipe("636766", "https://spoonacular.com/recipeImages/636766-556x370.jpg", "Cake with lemon, rosewater and pistachios", "Cake with lemon, rosewater and pistachios is a <b>lacto ovo vegetarian</b> dessert. One serving contains <b>333 calories</b>, <b>6g of protein</b>, and <b>19g of fat</b>. This recipe serves 12 and costs 69 cents per serving. 30 people were impressed by this recipe. It is brought to you by Foodista. A mixture of caster sugar, , ground almonds, and a handful of other ingredients are all it takes to make this recipe so yummy. From preparation to the plate, this recipe takes approximately <b>approximately 45 minutes</b>. Taking all factors into account, this recipe <b>earns a spoonacular score of 38%</b>, which is not so amazing. If you like this recipe, you might also like recipes such as <a href=\"https://spoonacular.com/recipes/lemon-ginger-cake-with-pistachios-188808\">Lemon-Ginger Cake with Pistachios</a>, <a href=\"https://spoonacular.com/recipes/yellow-lemon-cake-with-candied-lemons-and-pistachios-60493\">Yellow Lemon Cake With Candied Lemons And Pistachios</a>, and <a href=\"https://spoonacular.com/recipes/rosewater-pomegranate-sponge-cake-donna-hay-8-simones-kitchensimones-kitchen-589054\">Rosewater pomegranate sponge cake - Donna Hay #8 | Simone's KitchenSimone's Kitchen</a>.", "");


                getFragmentManager().beginTransaction().replace(R.id.flContainer, new FavoritesFragment()).commit();



            }
        });

        btnGoToEdit = view.findViewById(R.id.btnGoToEdit);
        btnGoToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFavoritesEditFragment();
            }
        });

        favoritesDB = new FavoritesDatabaseHelper(getActivity());
        row_id = new ArrayList<>();
        recipe_id = new ArrayList<>();
        image_URL = new ArrayList<>();
        title = new ArrayList<>();
        summary = new ArrayList<>();
        notes = new ArrayList<>();

        storeFavoritesDataInArrays();

        favoritesAdapter = new FavoritesAdapter(getContext(), row_id, recipe_id, image_URL, title, summary, notes);
        rvFavorites.setAdapter(favoritesAdapter);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //check if app is opened for the first time and if it is, set the sorting setting
    void setSortSettings() {
        sortDB = new SortFavoritesDatabaseHelper(getActivity());
        if (sortDB.getSortCount() < 1) {
            sortDB.addSort(1, 0, 0, 0);
        }

    }

    void goToFavoritesEditFragment() {
        FavoritesEditFragment newFragment = new FavoritesEditFragment ();

        //add a stack so we can click back button to go back
        System.out.println("HELLO");
        getFragmentManager().beginTransaction().replace(R.id.flContainer, newFragment).addToBackStack(null).commit();
    }

    void storeSortDataInArrays() {
        Cursor cursor = sortDB.readSortSetting();
        if(cursor.getCount() == 0) {
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()) {
                sortSettings.add(cursor.getString(0));
                sortSettings.add(cursor.getString(1));
                sortSettings.add(cursor.getString(2));
                sortSettings.add(cursor.getString(3));
                sortSettings.add(cursor.getString(4));

            }
        }
    }


    void storeFavoritesDataInArrays() {
        Cursor cursor = favoritesDB.readAllDataSorted(sortingCol, sortingOrder);
        if(cursor.getCount() == 0) {
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()) {
                row_id.add(cursor.getString(0));
                recipe_id.add(cursor.getString(1));
                image_URL.add(cursor.getString(2));
                title.add(cursor.getString(3));
                summary.add(cursor.getString(4));
                notes.add(cursor.getString(5));

            }
        }
    }


}