package com.project.forcedepartment.dao;

import com.project.forcedepartment.model.Worker;

import java.util.List;

public interface WorkerDao {

    List<Worker> getAllByRating();
    List<Worker> getAllByProfession(String profession);
    List<Worker> getAllByWorkObject(String workObject);
    List<Worker> getAllByName(String name);
    List<Worker> getAllByFilter(String name, String workObject, String profession, int rating);



}
