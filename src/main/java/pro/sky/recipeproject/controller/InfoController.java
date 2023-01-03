package pro.sky.recipeproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
@Tag(name = "Информация")
public class InfoController {

    @GetMapping
    @Operation(summary = "Информация о проекте")
    public String info() {
        return """
                                
                Имя ученика: Павел Васильев<br>
                Название проекта: Сайт с рецептами<br>
                Дата создания: 09.12.2022<br>
                Описание проекта:<br>
                Проект сайта с рецептами, реализованный на языке Java, с помощью технологий Maven и Spring Framework
                      
                """;
    }
}
