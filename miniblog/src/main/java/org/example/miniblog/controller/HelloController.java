package org.example.miniblog.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(value = "/hi")
    public  String welcome(){
        return "hello";
    }
}
