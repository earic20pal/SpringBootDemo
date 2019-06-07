package com.example.edunext.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/hello")
    String home(ModelMap modal) {
        modal.addAttribute("title","CRUD Example");
        return "hello";
    }

}
