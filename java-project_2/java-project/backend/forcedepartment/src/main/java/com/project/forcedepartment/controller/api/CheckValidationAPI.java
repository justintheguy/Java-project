package com.project.forcedepartment.controller.api;

import com.project.forcedepartment.dao.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class CheckValidationAPI {

    DatabaseManager databaseManager;

    @Autowired
    public CheckValidationAPI(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping(value = "/api/ifEmailExist/{email}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean checkEmailInUse(@PathVariable("email") String email){
        return databaseManager.checkIfEmailInUse(email);
    }

    @RequestMapping(value = "/api/checkUserIsExist/{email}:{password}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean checkUserIsExist(@PathVariable("email") String email,
                                                  @PathVariable("password") String password) {

        return databaseManager.checkValidLogin(email, password);
    }
}
