package pro.sky.recipeproject.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeproject.model.Ingredient;
import pro.sky.recipeproject.service.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") int id){
        return this.ingredientService.getIngredient(id);
    }

    @PostMapping
    public void addIngredient(Ingredient ingredient){
        this.ingredientService.addIngredient(ingredient);
    }
}
