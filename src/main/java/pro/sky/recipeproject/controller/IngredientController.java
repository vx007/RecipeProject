package pro.sky.recipeproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipeproject.model.Ingredient;
import pro.sky.recipeproject.service.IngredientService;

import java.util.Map;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента")
    public int addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента по id")
    public ResponseEntity<String> editIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        if (this.ingredientService.editIngredient(id, ingredient)) {
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента по id")
    public ResponseEntity<String> deleteIngredient(@PathVariable("id") int id) {
        if (this.ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok("Успешно");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингредиента по id")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") int id) {
        if (this.ingredientService.getIngredient(id) != null) {
            return ResponseEntity.ok(this.ingredientService.getIngredient(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Получение всех ингредиентов")
    public Map<Integer, Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }
}
