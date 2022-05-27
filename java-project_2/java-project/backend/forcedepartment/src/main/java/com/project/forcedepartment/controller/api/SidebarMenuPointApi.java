package com.project.forcedepartment.controller.api;

import com.project.forcedepartment.dao.DatabaseManager;
import com.project.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SidebarMenuPointApi {

    DatabaseManager databaseManager;

    @Autowired
    public SidebarMenuPointApi(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }


    @RequestMapping(value = "/api/getAllWorkerByProfession/{profession}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkerByProfession(
            @PathVariable("profession") String profession) {
        return databaseManager.getWorkersByProfession(profession);
    }


    @RequestMapping(value = "/api/getAllWorkerByWorkObject/{workObject}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkerByWorkObject(
            @PathVariable("workObject") String workObject) {
        return databaseManager.getWorkersByWorkObject(workObject);
    }


    @RequestMapping(value = "/api/getWorkerByExtraSearch/{name}/{workObject}/{profession}/{rating}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Worker> getWorkersByExtraFilter(
            @PathVariable("name") String name,
            @PathVariable("workObject") String workObject,
            @PathVariable("profession") String profession,
            @PathVariable("rating") int rating
    ) {
        return databaseManager.getAllByFilter(name, workObject, profession, rating);
    }

}
