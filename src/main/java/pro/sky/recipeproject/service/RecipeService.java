package pro.sky.recipeproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.Ingredient;
import pro.sky.recipeproject.model.Recipe;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeService {

    private final FileService fileService;

    private static LinkedHashMap<Integer, Recipe> recipes = new LinkedHashMap<>();

    private static int id;

    @Value("${dataFilePath}")
    private String dataFilePath;

    @Value("${recipesFileName}")
    private String dataFileName;

    public RecipeService(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    public int addRecipe(Recipe recipe) {
        recipes.put(++id, recipe);
        saveToFile();
        return id;
    }

    public boolean editRecipe(int id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteRecipe(int id) {
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    public Recipe getRecipe(int id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            return null;
        }
    }

    public Map<Integer, Recipe> getAllRecipes() {
        return recipes;
    }

    public Path createReport() throws IOException {
        Path path = Files.createTempFile(Path.of(dataFilePath), "temp", "report");
        for (Recipe recipe : recipes.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName() + "\n");
                writer.append("Время приготовления: " + recipe.getTime() + " минут\n");
                writer.append("Ингредиенты:\n");
                for (Ingredient ingredient : recipe.getIngredients()) {
                    writer.append(ingredient.getName() + " - " + ingredient.getCount() + " " + ingredient.getUnit() + "\n");
                }
                writer.append("Инструкция приготовления:\n");
                for (String step : recipe.getSteps()) {
                    writer.append(step + "\n");
                }
                writer.append("\n");
            }
        }
        return path;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.save(Path.of(dataFilePath, dataFileName), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.read(Path.of(dataFilePath, dataFileName));
            recipes = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
