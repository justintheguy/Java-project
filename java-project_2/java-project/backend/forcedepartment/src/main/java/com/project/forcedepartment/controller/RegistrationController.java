package com.project.forcedepartment.controller;

import com.project.forcedepartment.dao.DatabaseManager;
import com.project.forcedepartment.model.Profession;
import com.project.forcedepartment.model.User;
import com.project.forcedepartment.model.util.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Controller
public class RegistrationController {

    private DatabaseManager databaseManager;

    private static String webTitle = "Specialist department - Registration";
    private static String emailExist = "The email is already in used! Try again..";
    private static String wrongPassword = "The passwords don't match! Try again..";
    private static boolean isCorrectPassword = true;
    private static boolean isCorrectEmail = true;
    private static int workerId;

    @Autowired
    public RegistrationController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    //util class
    private Date dateConverter(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date actualDate = new Date();
        return dateFormat.parse(String.valueOf(actualDate));
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String registerSite(Model model) {

        model.addAttribute("title", webTitle);
        model.addAttribute("User", new User());
        model.addAttribute("UserTypes", Arrays.asList(UserTypes.values()));
        model.addAttribute("isCorrectPassword", isCorrectPassword);
        model.addAttribute("isCorrectEmail", isCorrectEmail);
        if (isCorrectEmail == false) {
            model.addAttribute("emailExist", emailExist);
        } else if (isCorrectPassword == false){
            model.addAttribute("wrongPassword", wrongPassword);
        }

        setAllCheckerToTrue();
        return "registration";

    }

    //util class
    private void setAllCheckerToTrue() {
        isCorrectPassword = true;
        isCorrectEmail = true;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public String saveRegisterUserData(@RequestParam("password") String password,
                                       @RequestParam("passwordAgain") String passwordAgain,
                                       @ModelAttribute User user) throws ParseException {

        if (password.equals(passwordAgain)) {
            if (!databaseManager.checkIfEmailInUse(user.getEmail())) {
                if (user.getUserType().equals(String.valueOf(UserTypes.WORKER))) {
                    User worker = new User(
                            user.getFirstName(), user.getLastName(), user.getBirthOfDate(),
                            user.getUserType(), user.getEmail());
                    workerId = databaseManager.registerRegularUser(worker, user.getPassword());
                    return "redirect:/register/worker";
                } else if (user.getUserType().equals(String.valueOf(UserTypes.USER))) {
                    databaseManager.registerRegularUser(user, user.getPassword());
                }
            } else {
                isCorrectEmail = false;
                return "redirect:/register";
            }
        } else {
            isCorrectPassword = false;
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/register/worker", method = {RequestMethod.GET})
    public String remainingDataForWorker(Model model) {

        model.addAttribute("title", webTitle);
        model.addAttribute("ProfessionObject", new Profession());
        model.addAttribute("professions", databaseManager.getAllProfession());

        return "register-worker";
    }


    //@RequestParam("professionName") String workerProfession
    @RequestMapping(value = "/register/worker", method = {RequestMethod.POST})
    public String saveRemainingDataForWorker(
            @RequestParam("description") String description,
            @RequestParam("phone_number") String phoneNumber
    ) {

        databaseManager.registerWorker(workerId, phoneNumber, description);

        return "redirect:/login";
    }
}