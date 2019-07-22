package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String displayHomePage(){
        return "home";
    }

    // about

    @GetMapping(value = "/about")
    public String displayAbout(){
        return "about";
    }
}
