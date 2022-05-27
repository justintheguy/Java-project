package com.project.forcedepartment.controller.api;

import com.project.forcedepartment.dao.DatabaseManager;
import com.project.forcedepartment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfileApi {

    DatabaseManager databaseManager;

    @Autowired
    public UserProfileApi(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping(value = "/api/getUserById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User getWorkerById(
            @PathVariable("userId") int userId
    ) { return databaseManager.getDataAboutUser(userId);}
}
