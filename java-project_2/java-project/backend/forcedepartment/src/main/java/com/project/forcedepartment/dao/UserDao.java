package com.project.forcedepartment.dao;

import com.project.forcedepartment.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    // add new regular user

    int addNewRegularUser(User user, String hashedPassword);

    // add new worker

    void addNewWorker(int workerId, String phoneNumber, String description);

    // get all data about user

    User getDataAboutUser(int id);

    List<User> getAllDataAboutUser();

    // check if user exist - email

    boolean checkIfUserExists(String email);

    // login check if user exist - email + password

    boolean checkIfValidLogin(String email, String password);

    // update

    void editRegularProfile(int userId, String firstName, String lastName, String birthOfDate, String email, String password);

    void editWorkerProfile(int userId, String firstName, String lastName, String birthOfDate, String email, String password, String description, String phoneNumber, boolean isAvailable);

    // worker with experience year - profile

    Map<String, Integer> getProfessionWithExperience(int userId);

    // save

    void saveProfessionWithExperience(int userId, Map<String, Integer> professionAndExperience);

    int getLatestId(String tableName);

    int getUserIdByEmail(String email);

}
