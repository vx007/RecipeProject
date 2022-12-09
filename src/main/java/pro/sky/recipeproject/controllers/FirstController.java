package pro.sky.recipeproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String Run(){
        return "Приложение запущено";
    }
}
