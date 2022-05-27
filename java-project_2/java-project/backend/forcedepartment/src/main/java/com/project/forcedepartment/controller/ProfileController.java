package com.project.forcedepartment.controller;

import com.project.forcedepartment.dao.DatabaseManager;
import com.project.forcedepartment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private DatabaseManager databaseManager;
    private String webTitle = "Special department |";

    @Autowired
    public ProfileController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping(value="/profile/{userId}", method={RequestMethod.GET})
    public String userProfile(@PathVariable int userId,
                              Model model,
                              HttpServletRequest request
                              ) {

        if (request.getSession().getAttribute("email") != null) {
            int sessionUserId = databaseManager.getUserIdByEmail(String.valueOf(request.getSession().getAttribute("email")));
            model.addAttribute("sessionUserId", sessionUserId);
        }

        List<User> userData = new ArrayList<>();
        userData.add(databaseManager.getDataAboutUser(userId));

        model.addAttribute("userData", userData);
        model.addAttribute("title",
                webTitle+= " "+databaseManager.getDataAboutUser(userId).getFirstName()+" "+databaseManager.getDataAboutUser(userId).getLastName());
        return "userProfile";
    }

}
