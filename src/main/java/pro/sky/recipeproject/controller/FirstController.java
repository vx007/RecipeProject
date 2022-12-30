package pro.sky.recipeproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Статус")
public class FirstController {

    @GetMapping
    @Operation(summary = "Статус приложения")
    public String Run() {
        return "Приложение запущено";
    }
}
