package com.example.edunext.controller.home;

import com.example.edunext.model.User;
import com.example.edunext.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/hello")
    public ModelAndView getAllUsers() {
        ModelAndView model = new ModelAndView();

        List<User> usersList = userService.getAllUser();
        model.addObject("usersList", usersList);

        model.setViewName("hello");
        return model;
    }
}
