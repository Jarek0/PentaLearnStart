/*
 * Copyright 2011-08-31 the original author or authors.
 */
package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.model.Person;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {
    
    protected Logger logger = Logger.getLogger(getClass());
    
    @RequestMapping("/")
    public ModelAndView home() {

        Person person=new Person();
        person.setName("lukasz");
        ModelAndView modelAndView=new ModelAndView("home");
        modelAndView.addObject("person",person);
        return modelAndView;
    }
    
}
