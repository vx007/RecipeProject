package pro.sky.recipeproject.service;

import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientService {
    private static final Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();
    private static int id;

    public int addIngredient(Ingredient ingredient){
        ingredients.put(++id, ingredient);
        return id;
    }

    public boolean editIngredient(int id, Ingredient ingredient){
        if(ingredients.containsKey(id)){
            ingredients.put(id, ingredient);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteIngredient(int id){
        if(ingredients.containsKey(id)){
            ingredients.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Ingredient getIngredient(int id){
        if (ingredients.containsKey(id)){
            return ingredients.get(id);
        }else {
            return null;
        }
    }

    public Map<Integer, Ingredient> getAllIngredients(){
        return ingredients;
    }
}
