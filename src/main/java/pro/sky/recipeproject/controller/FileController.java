package pro.sky.recipeproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipeproject.service.FileService;

import java.io.*;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы")
public class FileController {

    private final FileService fileService;

    @Value("${dataFilePath}")
    private String dataFilePath;

    @Value("${recipesFileName}")
    private String recipesFileName;

    @Value("${ingredientsFileName}")
    private String ingredientsFileName;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/export-recipes")
    @Operation(summary = "Скачать файл рецептов")
    public ResponseEntity<InputStreamResource> exportRecipes() throws FileNotFoundException {
        File file = fileService.get(Path.of(dataFilePath, recipesFileName));

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import-recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Закачать файл рецептов")
    public ResponseEntity<Void> importRecipes(@RequestParam MultipartFile file) {
        Path dataPath = Path.of(dataFilePath, recipesFileName);
        fileService.clear(dataPath);
        File dataFile = fileService.get(dataPath);

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping(value = "/import-ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Закачать файл ингредиентов")
    public ResponseEntity<Void> importIngredients(@RequestParam MultipartFile file) {
        Path dataPath = Path.of(dataFilePath, ingredientsFileName);
        fileService.clear(dataPath);
        File dataFile = fileService.get(dataPath);

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}
