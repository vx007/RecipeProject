package pro.sky.recipeproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeproject.model.Ingredient;
import pro.sky.recipeproject.service.IngredientService;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public int addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        if (this.ingredientService.editIngredient(id, ingredient)) {
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("id") int id) {
        if (this.ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") int id) {
        if (this.ingredientService.getIngredient(id) != null) {
            return ResponseEntity.ok(this.ingredientService.getIngredient(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Map<Integer, Ingredient> getAllIngredients(){
        return this.ingredientService.getAllIngredients();
    }
}
