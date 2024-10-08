package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-Vindo ao Backend do SMN!";
    }
}
