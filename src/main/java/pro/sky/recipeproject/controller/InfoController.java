package pro.sky.recipeproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping(path = "/info")
    public String info(){
        return """
                
                Имя ученика: Павел Васильев<br>
                Название проекта: Сайт с рецептами<br>
                Дата создания: 09.12.2022<br>
                Описание проекта:<br>
                Проект сайта с рецептами, реализованный на языке Java, с помощью технологий Maven и Spring Framework
                      
                """;
    }
}
