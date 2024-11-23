package mr.demonid.spring.hw5.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyStartController {

    @GetMapping
    public String start() {
        return "/index";
    }
}
