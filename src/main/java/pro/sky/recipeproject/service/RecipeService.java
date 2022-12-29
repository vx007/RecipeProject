package pro.sky.recipeproject.service;

import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.Recipe;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeService {
    private static final Map<Integer, Recipe> recipes = new LinkedHashMap<>();
    private static int id;

    public int addRecipe(Recipe recipe){
        recipes.put(++id, recipe);
        return id;
    }

    public boolean editRecipe(int id, Recipe recipe){
        if(recipes.containsKey(id)){
            recipes.put(id, recipe);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteRecipe(int id){
        if (recipes.containsKey(id)){
            recipes.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Recipe getRecipe(int id){
        if(recipes.containsKey(id)){
            return recipes.get(id);
        } else {
            return null;
        }
    }

    public Map<Integer, Recipe> getAllRecipes(){
        return recipes;
    }

}
