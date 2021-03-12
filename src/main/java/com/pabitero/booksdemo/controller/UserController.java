package com.pabitero.booksdemo.controller;

import com.pabitero.booksdemo.entity.User;
import com.pabitero.booksdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ModelAndView getUsers() {
        log.info("Inside getUsers in UserController");
        ModelAndView mav = new ModelAndView("books/userList");
        mav.addObject("users", userService.findAllUsers());
        return mav;
    }

    @PostMapping("/users")
    @ResponseBody
    public String createUser(User user) {
        log.info("Inside createUser in UserController");
        userService.saveUser(user);
        return "user/success";
    }

    @GetMapping("/login")
    public ModelAndView goToLoginPage(HttpServletRequest request) {
        log.info("Inside goToLoginPage in UserController");
        ModelAndView mav = new ModelAndView();

        String message;
        if(request.getSession().getAttribute("user") != null) {
            mav.setViewName("books/index");
            message = "User already in session";
            mav.addObject("message", message);
            return mav;
        } else {
            mav.setViewName("books/login");
            mav.addObject("user", new User());
            return mav;
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ModelAndView verifyLogin(User user, HttpServletRequest request) {
        log.info("Inside verifyLogin in UserController");
        String email = user.getEmail();
        User userInDb = userService.findUserByEmail(email);

        String message;

        ModelAndView mav = new ModelAndView();
        if (userInDb != null) {
            String password = user.getPassword();

            if (userInDb.getPassword().equals(password)) {
                log.info("Passwords match");
                HttpSession session = request.getSession();
                session.setAttribute("user", userInDb);
                mav.setViewName("books/index");
                mav.addObject("user", userInDb);
                message = String.format("Welcome %s!", userInDb.getFirstName());
                mav.addObject("message", message);
                return mav;
            } else {
                log.info("Incorrect Password");
                mav.setViewName("books/login");
                message = "Incorrect Login Information";
                mav.addObject("message", message);
                return mav;
            }
        } else {
            log.info("Username not found");
            mav.setViewName("books/login");
            message = "Username was not found";
            mav.addObject("message", message);
            return mav;
        }
    }

    @GetMapping("/register")
    public ModelAndView goToRegisterPage(HttpServletRequest request) {
        log.info("Inside goToRegisterPage in UserController");
        ModelAndView mav = new ModelAndView("books/registration");
        mav.addObject(new User());
        mav.addObject(request.getSession());
        return mav;
    }

    @PostMapping("/register")
    @ResponseBody
    public ModelAndView processRegistration(User user) {
        log.info("Inside processRegistration in UserController");
        userService.saveUser(user);
        ModelAndView mav = new ModelAndView("books/success");
        mav.addObject(user);
        return mav;
    }
}
