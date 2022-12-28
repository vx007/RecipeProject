package pro.sky.recipeproject.service;

import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientService {
    private static final Map<Integer, Ingredient> ingredients = new LinkedHashMap<>();
    private static int id;

    public void addIngredient(Ingredient ingredient){
        ingredients.put(++id, ingredient);
    }

    public Ingredient getIngredient(int id){
        if (ingredients.containsKey(id)){
            return ingredients.get(id);
        }else {
            throw new RuntimeException("Нет такого id");
        }
    }
}
