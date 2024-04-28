package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {
    
    @RequestMapping("/game")
    public ModelAndView  hello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("View.html");
        return modelAndView;
    }
}
