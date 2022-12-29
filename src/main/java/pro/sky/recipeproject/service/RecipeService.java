package pro.sky.recipeproject.service;

import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.Recipe;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeService {
    private static final Map<Integer, Recipe> recipes = new LinkedHashMap<>();
    private static int id;

    public void addRecipe(Recipe recipe){
        recipes.put(++id, recipe);
    }

    public Recipe getRecipe(int id){
        if(recipes.containsKey(id)){
            return recipes.get(id);
        } else {
            throw new RuntimeException("Нет такого id");
        }
    }


}
