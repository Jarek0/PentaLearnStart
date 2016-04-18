/*
 * Copyright 2011-08-31 the original author or authors.
 */
package com.pentalearn.pentalearnstart.controller;

import com.pentalearn.pentalearnstart.model.Person;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    protected Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home", "command", new Person());
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("SpringWeb")Person person, ModelMap model){
        model.addAttribute("name", person.getName());
        model.addAttribute("id", person.getId());

        return "personInfo";
    }
}
