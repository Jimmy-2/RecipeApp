# RecipeApp
Android project for Codepath

Group members: (Add your name guys. Delete this once everyone has added their names) 

[Jonathan Lin](https://github.com/LimberLumberSlumber) - 1st Tab: Random Recipe Screen

[](https://github.com/Avoracity) - 2nd Tab: Search Recipe by Keyword/Ingredient Screen 

[Alexandra Ernest](https://github.com/AlexandraErnest) - 3rd Tab: Find Recipe by Category Screen

[Jimmy Wu](https://github.com/Jimmy-2) - 4th Tab: Favorites Screen

[Isaac Anzures](https://github.com/ianzures) - Recipe Screen


## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)
3. [Gifs](#Gifs)

## Overview
### Description
A recipe app for searching, viewing, and saving recipes.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Food**
- **Mobile: Android**
- **Story: saves and searches users interest of food recipes and provides new recipes as well.**
- **Market: Home/Personal Cooking**
- **Habit: This app could be used whenever the user wants to cook with an existing recipe in mind or a new recipe**
- **Scope: The user would search for recipes of interest through searching recipes by ingredients or the recipe's name. The user can also search for a list of recipes based on food categories. Users can save recipes into a favorites list. Clicking Variations of the recipes may pique the user interest and may broaden their taste pallete. **

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* - [ ] User can login to save recipe(if we do not implement a login system, we will use sqlite database to save the recipes)
* - [ ] A fragment screen that displays the recipe and its instructions. User can add the recipe to a favorites database table. This screen will be called the recipeFragment.
* - [ ] The 1st tab will be a fragment screen that displays a random recipe and its instructions. This will follow the same layout as recipeFragment and will be called randomRecipeFragment.
* - [ ] The 2nd tab will be a fragment screen that allows user to search recipes either by recipe keyword or by ingredients. The screen will display a recyclerview/list of recipes that fit the searched parameters. Clicking on any of these recyclerview/list items will lead to a recipeFragment for that item. Will be called searchRecipeFragment
* - [ ] The 3rd tab will be a screen called searchCategoryFragment that allows the user to find recipes based on food categories. Clicking on any of the categories will lead to another screen called listCategoryFragment that will display a list of recipes based on the category. Will mostly follow the same layout as the searchRecipeFragment but without the search bar. 
* - [x] The 4th tab will be a screen called favoritesFragment. It will display a recyclerview populated with data from the favorites database. Users can also delete any of these items. Clicking on any of the recipes will lead to the recipeFragment for that recipe.

### 2. Screen Archetypes

* Home/1st tab: Random Recipe 
    * The first screen the user sees - Recipe screen of a random recipe.  
* 2nd tab: Search Recipe by keyword/ingredient
    * The screen will display a list of recipes based on searched parameters. If a user clicks on a recipe in the list, it will lead to the recipe screen.
* 3rd tab: Search Recipe by Category
    * User is able to find recipes based on chosen category. If a user clicks on a category, it will lead to a screen that will display a list of recipes based on the selected category.
* 4th tab: Favorites List
    * User is see a list of their favorited recipes and can edit the list. If user clicks on a recipe in the list, it will lead to the recipe screen.

### 3. Navigation

**Bottom Navigation Bar** (4 Tabs) 
* Random Recipe
* Search Recipe by Keyword/Ingredient
* Search Recipe by Category
* Favorites List


**Flow Navigation** (Screen to Screen)

* Home/1st tab: recipe screen of a random recipe
* 2nd tab: find recipe by search -> recipe screen
* 3rd tab: find recipe by category -> recipe list screen -> recipe screen  
* 4th tab: saved favorite list -> recipe screen

## Wireframes
Figma link: 
https://www.figma.com/file/OBT1KtDpdZsgEIyyqdFPRy/RecipeApp?node-id=0%3A1

### [BONUS] Digital Wireframes & Mockups
<img src="https://user-images.githubusercontent.com/66243489/138971497-b5874eb5-12de-40e0-a51c-11bde74f915e.png" height=500>
[BONUS] Interactive Prototype

## Schema 
### Models
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | favoriteIdDB      | String   | generated column id for the favorited recipe in database (favorites tab) |
   | recipeIdDB        | String | id of a favorited recipe stored in database that will be used to access it's recipe screen.|
   | titleDB        | String    | title of favorited recipe stored in database |
   | imageDB       | image   | link of favorited recipe's image stored in database |
   | recipeId | String   | id of recipe that will be used in api's url |
### Networking
##### Spooncular API
- Base URL - [https://api.spoonacular.com](https://spoonacular.com/food-api/docs)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /recipes/findByIngredients | gets recipes that contain the ingredients
    `GET`    | /recipes/complexSearch | gets recipes that contain included parameters (keywords)
    `GET`    | /recipes/{id}/information | gets information and instructions of the recipe by id
    `GET`    | /recipes/informationBulk?ids={} | gets information and isntructions of recipes by ids
    `GET`    | /recipes/random?number={}&tags={} | gets list of random recipes by number and tag
    
   - Home Screen (random recipe)
      - (Read/GET) Get a list of random recipes
         ```swift
         AsyncHttpClient client = new DefaultAsyncHttpClient();
		client.prepare("GET", "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?tags=vegetarian%2Cdessert&number=1")
			.setHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
			.setHeader("x-rapidapi-key", "SIGN-UP-FOR-KEY")
			.execute()
			.toCompletableFuture()
			.thenAccept(System.out::println)
			.join();

		client.close();
         ```
      - (Create/POST) Save to Favorites list
      - (Delete) Remove from Favorites list
   - Find Recipe Screen (search by recipe keyword/ingredient)
      - (Read/GET) Find recipes that use as many of the given ingredients as possible and have as little as possible missing ingredients. 
        ```java
         AsyncHttpClient client = new DefaultAsyncHttpClient();
        client.prepare("GET", "https://spoonacular-recipe-food-nutrition-           v1.p.rapidapi.com/recipes/findByIngredients?ingredients=apples%2Cflour%2Csugar&number=5&ignorePantry=true&ranking=1")
         .setHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
         .setHeader("x-rapidapi-key", "SIGN-UP-FOR-KEY")
         .execute()
         .toCompletableFuture()
          .thenAccept(System.out::println)
          .join();

         client.close();
         ```

    
   - Find Recipe Screen (search by food/recipe category)
      - (Read/GET) Find a recipe that is under a specific category 
           ```swift
           AsyncHttpClient client = new DefaultAsyncHttpClient();
           client.prepare("GET", "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/searchComplex?limitLicense=%3CREQUIRED%3E&offset=0&number=10&minIron=0&minCalcium=0&maxVitaminB2=1000&maxMagnesium=1000&minPotassium=0&maxVitaminB6=1000&intolerances=peanut%2C%20shellfish&maxVitaminB5=1000&minFolicAcid=0&minVitaminA=0&maxSodium=1000&maxSugar=1000&maxVitaminA=5000&maxFluoride=1000&minFluoride=0&minVitaminB1=0&minCholine=0&ranking=2&minFat=5&maxVitaminB1=1000&minVitaminB12=0&maxSelenium=1000&minZinc=0&minFolate=0&maxManganese=1000&maxVitaminB12=1000&maxPotassium=1000&maxIron=1000&minSelenium=0&minVitaminK=0&maxFiber=1000&minSodium=0&maxCopper=1000&minCalories=150&maxCholine=1000&minCholesterol=0&maxVitaminE=1000&minProtein=5&minVitaminB3=0&minVitaminB6=0&maxIodine=1000&excludeIngredients=coconut%2C%20mango&maxProtein=100&minMagnesium=0&minCarbs=5&cuisine=american&maxCaffeine=1000&maxSaturatedFat=50&maxVitaminK=1000&minAlcohol=0&minIodine=0&query=burger&minSaturatedFat=0&includeIngredients=onions%2C%20lettuce%2C%20tomato&minVitaminE=0&maxCalcium=1000&minFiber=0&minVitaminC=0&maxZinc=1000&maxCalories=1500&maxAlcohol=1000&minPhosphorus=0&minVitaminD=0&minVitaminB2=0&minSugar=0&maxFolate=1000&type=main%20course&maxCholesterol=1000&maxVitaminB3=1000&minCaffeine=0&minVitaminB5=0&maxFolicAcid=1000&maxCarbs=100&maxVitaminD=1000&equipment=pan&maxFat=100&minCopper=0&maxVitaminC=1000&maxPhosphorus=1000&minManganese=0")
	      .setHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
	      .setHeader("x-rapidapi-key", "SIGN-UP-FOR-KEY")
	      .execute()
	      .toCompletableFuture()
	      .thenAccept(System.out::println)
	      .join();

         client.close();
         ```
   - Recipe Screen (Displays information about the recipe such as name, description, ingredients, instructions, etc)
      - Get a recipe's id (String) from the other screens through bundle
      - (Read/GET)  Get recipe information such as name, description, ingredients, instructions, images, etc from the endpoint, /recipes/{id}/information and replacing the {id} with the passed String id. Use codepath's Async Http Client library to access and parse the json.
         ```swift
           String recipeId = getArguments().getString("Bundle Key");
           String recipe_API = "https://api.spoonacular.com/recipes/recipeId/information?apiKey=YOUR-API-KEY&includeNutrition=true";

           AsyncHttpClient client = new AsyncHttpClient();
           client.get(recipe_API, new JsonHttpResponseHandler() {
               @Override
               public void onSuccess(int statusCode, Headers headers, JSON json) {
                   Log.d(TAG, "onSuccess");
                   JSONObject jsonObject = json.jsonObject;
                   String testing = "";
                   try {
                       /*
                        Parse information from the json and put them into textviews.
                       /*
                   } catch (JSONException e) {
                       Log.e(TAG, "Hit json exception", e);
                       e.printStackTrace();
                   }
               }
               @Override
               public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                   Log.d(TAG, "onFailure");
               }
           });
         ```
   - Favorites Screen (list of favorited recipes)
      - (Read/GET)  Recipe ids, names, images and summaries by the stored strings found in the favorites list sqlite database.
      - Populate recyclerview with each recyclerview item as a singular recipe with their names, images, summaries shown. OnBindViewHolder Onclick will enter recipe screen and pass id as a string to recipe screen using Bundle so that the recipe screen can use the api url GET /recipes/{id}/information to display the recipe information.
         ```swift
         @Override
         public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         ......
            holder.favoritesLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   AppCompatActivity activity = (AppCompatActivity) view.getContext();
                   RecipeFragment fragment = new RecipeFragment();
                   //using bundle to pass data to another fragment
                   Bundle args = new Bundle();
                   //key, value
                   args.putString("RecipeId", String.valueOf(recipeIdDB.get(position)));
                   fragment.setArguments(args);
                   //add a stack so we can click back button to go back to favorites fragment (add function in mainactivity to pop stack)
                   activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).addToBackStack(null).commit();
               }
            });
         }
         ```
      - (Delete) Delete existing recipe from favorites list with a button onclick on the right side of each recyclerview item row. 
      
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]

## Gifs:

##### Unit 10: 11/9/2021

1st tab:

2nd tab:

3rd tab:

4th tab: Favorites Screen: 

<img src='https://github.com/Jimmy-2/RecipeApp/blob/master/JimmyRecipe1.gif?raw=true' title='Favorites Screen 11/9/2021 Unit 10' width='' alt='Favorites Screen 11/9/2021 Unit 10' />

Recipe Screen:
