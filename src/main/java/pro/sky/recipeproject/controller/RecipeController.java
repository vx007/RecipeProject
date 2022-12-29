package pro.sky.recipeproject.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipeproject.model.Recipe;
import pro.sky.recipeproject.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") int id){
        return this.recipeService.getRecipe(id);
    }

    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe){
        this.recipeService.addRecipe(recipe);
    }
}
