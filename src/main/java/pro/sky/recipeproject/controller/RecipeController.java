package pro.sky.recipeproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeproject.model.Recipe;
import pro.sky.recipeproject.service.RecipeService;

import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public int addRecipe(@RequestBody Recipe recipe){
        return this.recipeService.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe){
        if(this.recipeService.editRecipe(id, recipe)){
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") int id){
        if(this.recipeService.deleteRecipe(id)){
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") int id){
        if (this.recipeService.getRecipe(id) != null){
            return ResponseEntity.ok(this.recipeService.getRecipe(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Map<Integer, Recipe> getAllRecipes(){
        return this.recipeService.getAllRecipes();
    }
}
