package com.project.forcedepartment.controller.api;

import com.project.forcedepartment.dao.DatabaseManager;
import com.project.forcedepartment.model.User;
import com.project.forcedepartment.model.Worker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GetAllUsersApi {

    DatabaseManager databaseManager;
    private int workerId;

    @Autowired
    public GetAllUsersApi(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAllRegularUser() {
        return databaseManager.getAllDataAboutUser();
    }

    @ResponseBody @RequestMapping(value = "/api/getAllUser", method = RequestMethod.POST)
    public String addRegularUser(@RequestBody String userJson) throws JSONException, ParseException {
        JSONObject user = new JSONObject(userJson);

        String firstName = user.getString("firstName");
        String lastName = user.getString("lastName");
        String birthOfDate = user.getString("birthOfDate");
        String userType = user.getString("userType");
        String password = user.getString("password");
        String email = user.getString("email");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthOfDateParsed = formatter.parse(birthOfDate);

        User newUser = new User(firstName, lastName, birthOfDateParsed, userType, email);
        workerId = databaseManager.registerRegularUser(newUser, password);
        return "User created";
    }

    @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Worker> getAllWorker() {
        return databaseManager.getWorkersByRating();
    }

    @ResponseBody @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.POST)
    public String addWorker(@RequestBody String workerJson) throws JSONException {
        JSONObject worker = new JSONObject(workerJson);

        String description = worker.getString("description");
        String phoneNumber = worker.getString("telephoneNumber");

        databaseManager.registerWorker(databaseManager.getLatestId("website_user") + 1, phoneNumber, description);
        return "Worker created";
    }

}
