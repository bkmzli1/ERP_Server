package ru.bkmz.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.util.HashMap;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class MainController {


    @GetMapping("")
    public String main(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("frontendData", data);

        return "index";
    }

    @GetMapping("/home")
    public String mainHome(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("frontendData", data);

        return "index";
    }

    @GetMapping("/information-support")
    public String mainIS(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("frontendData", data);

        return "index";
    }

    @GetMapping("/back-office")
    public String mainBO(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("frontendData", data);

        return "index";
    }

    @GetMapping("/questions")
    public String mainQuestions(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("frontendData", data);

        return "index";
    }


    @GetMapping("/statistics")
    public String mainStatistics(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("frontendData", data);

        return "index";
    }


}
