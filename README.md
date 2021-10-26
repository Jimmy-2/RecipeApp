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
* 2nd tab: Search Recipe by Recipe/Ingredient
    * The screen will display a list of recipes based on searched parameters. If a user clicks on a recipe in the list, it will lead to the recipe screen.
* 3rd tab: Search Recipe by Category
    * User is able to find recipes based on chosen category. If a user clicks on a category, it will lead to a screen that will display 
* 4th tab: Favorites List
    * User is see a list of their favorited recipes and can edit the list. If user clicks on a recipe in the list, it will lead to the recipe screen.

### 3. Navigation

**Bottom Navigation Bar** (Tab to Screen)

* Search recipe by keyword 
* Search recipe by ingredients
* Favorites list 

**Flow Navigation** (Screen to Screen)

* Home -> find recipe by ...
   * there are three find recipe polymorphic screens
   * by search, by ingrediant, by food category
* find recipe by search -> recipe screen
   
* saved favorite list -> recipe screen

## Wireframes
Figma
https://www.figma.com/file/OBT1KtDpdZsgEIyyqdFPRy/RecipeApp?node-id=0%3A1

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/4UpQTvr.png" height=200>
### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
