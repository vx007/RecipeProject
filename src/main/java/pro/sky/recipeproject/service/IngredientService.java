package pro.sky.recipeproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.Ingredient;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientService {

    private final FileService fileService;

    private static LinkedHashMap<Integer, Ingredient> ingredients = new LinkedHashMap<>();

    private static int id;

    @Value("${dataFilePath}")
    private String dataFilePath;

    @Value("${ingredientsFileName}")
    private String dataFileName;

    public IngredientService(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    public int addIngredient(Ingredient ingredient) {
        ingredients.put(++id, ingredient);
        saveToFile();
        return id;
    }

    public boolean editIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteIngredient(int id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    public Ingredient getIngredient(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            return null;
        }
    }

    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.save(Path.of(dataFilePath, dataFileName), json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.read(Path.of(dataFilePath, dataFileName));
            ingredients = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
