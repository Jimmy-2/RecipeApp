# RecipeApp
Android project for Codepath

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

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

* User can login to save recipe(if we do not implement a login system, we will use sqlite database to save the recipes)
* A fragment screen that displays the recipe and its instructions. User can add the recipe to a favorites database table. This screen will be called the recipeFragment.
* The 1st tab will be a fragment screen that displays a random recipe and its instructions. This will follow the same layout as recipeFragment and will be called randomRecipeFragment.
* The 2nd tab will be a fragment screen that allows user to search recipes either by recipe keyword or by ingredients. The screen will display a recyclerview/list of recipes that fit the searched parameters. Clicking on any of these recyclerview/list items will lead to a recipeFragment for that item. Will be called searchRecipeFragment
* The 3rd tab will be a screen called searchCategoryFragment that allows the user to find recipes based on food categories. Clicking on any of the categories will lead to another screen called listCategoryFragment that will display a list of recipes based on the category. Will mostly follow the same layout as the searchRecipeFragment but without the search bar. 
* The 4th tab will be a screen called favoritesFragment. It will display a recyclerview populated with data from the favorites database. Users can also delete any of these items. Clicking on any of the recipes will lead to the recipeFragment for that recipe.

### 2. Screen Archetypes

* Home/1st tab: Random Recipe 
    * The first screen the user sees - Recipe screen of a random recipe.  
* 2nd tab: Search Recipe by keyword/Ingredient
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
- Base URL - [https://api.spoonacular.com](https://api.spoonacular.com)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /recipes/findByIngredients | gets recipes that contain the ingredients
    `GET`    | /recipes/complexSearch | gets recipes that contain included parameters (keywords)
    `GET`    | /recipes/{id}/information | gets information and instructions of the recipe by id
    `GET`    | /recipes/informationBulk?ids={} | gets information and isntructions of recipes by ids
    `GET`    | /recipes/random?number={}&tags={} | gets list of random recipes by number and tag
    
   - Home Screen (random recipe)
      - (Read/GET) Query all posts where user is author
         ```swift
         let query = PFQuery(className:"Post")
         query.whereKey("author", equalTo: currentUser)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let posts = posts {
               print("Successfully retrieved \(posts.count) posts.")
           // TODO: Do something with posts...
            }
         }
         ```
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
   - Find Recipe Screen (search by recipe keyword/ingredient)
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
   - Find Recipe Screen (search by food/recipe category)
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
   - Favorites Screen (list of favorited recipes)
      - (Read/GET)  Recipe ids, names, images and summaries by the stored strings found in the favorites list sqlite database.
      - Populate recyclerview with each recyclerview item as a singular recipe with their names, images, summaries shown. Pass id as a string to recipe screen using Bundle so that the recipe screen can use the api url GET /recipes/{id}/information to display the recipe information.
      
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]

- 
