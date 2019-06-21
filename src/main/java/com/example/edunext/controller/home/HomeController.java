package com.example.edunext.controller.home;

import com.example.edunext.model.User;
import com.example.edunext.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    String home(ModelMap modal) {
        modal.addAttribute("title","CRUD Example");


        return "index";
    }
    
    @RequestMapping("/hello")
    public ModelAndView getAllUsers() {
        ModelAndView model = new ModelAndView();

        List<User> usersList = userService.getAllUser();
        model.addObject("usersList", usersList);

        model.setViewName("hello");
        return model;
    }

    @RequestMapping("/hello1")
    public ModelAndView getAllUsers1() {
        ModelAndView model = new ModelAndView();

        /*List<User> usersList = userService.getAllUser1();
        model.addObject("usersList", usersList);*/

        model.setViewName("test");
        return model;
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

}
