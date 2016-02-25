package com.prolificidea.templates.tsw.web.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class AppUserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root() {
        return new ModelAndView("/application/dashboard");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String userLogin() {
        return "appuser/login";
    }

    @RequestMapping(value = "/loginPassed", method = RequestMethod.GET)
    public ModelAndView loginPassed(Principal principal, HttpSession session) {

        return new ModelAndView("/application/dashboard", null);
    }

}
