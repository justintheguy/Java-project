package com.project.forcedepartment.controller;

import com.project.forcedepartment.dao.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainPageController {

    private String webTitle = "Special department";
    private String webCopyright = "Special department test footer";
    private String specificWorkers = "There are some workers who might you like";

    private DatabaseManager databaseManager;

    @Autowired
    public MainPageController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;}

    @RequestMapping(value = "/", method={RequestMethod.GET})
    public String mainPage(Model model, HttpServletRequest request) {

        model.addAttribute("title", webTitle);
        model.addAttribute("webCopyright", webCopyright);
        model.addAttribute("specificWorkers", specificWorkers);
        model.addAttribute("workersByRating", databaseManager.getWorkersByRating());
        //userId to model, and add to User class the same
        if (request.getSession().getAttribute("email") != null) {
            int sessionUserId = databaseManager.getUserIdByEmail(String.valueOf(request.getSession().getAttribute("email")));
            model.addAttribute("sessionUserId", sessionUserId);
        }

        return "mainpage";
    }

    @RequestMapping(value = "/", method={RequestMethod.POST})
    public String mainPagePost(Model model) {
        return "";
    }

    @RequestMapping(value = "/logout", method={RequestMethod.POST})
    public String logout(HttpSession session, HttpServletRequest request) {
        request.getSession();
        session.invalidate();
        return "redirect:/";
    }

}
