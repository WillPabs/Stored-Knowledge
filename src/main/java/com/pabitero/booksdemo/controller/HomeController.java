package com.pabitero.booksdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            log.info("Entering home page");
            ModelAndView mav = new ModelAndView();
            mav.setViewName("books/index");
            return mav;
        } else {
            log.info("Session already exists, redirecting to user account home page");
            ModelAndView mav = new ModelAndView();
            mav.setViewName("books/index");
            mav.addObject("user", request.getSession().getAttribute("user"));
            return mav;
        }
    }


}
