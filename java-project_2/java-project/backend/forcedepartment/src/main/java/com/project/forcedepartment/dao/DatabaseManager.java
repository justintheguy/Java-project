package com.project.forcedepartment.dao;

import com.project.forcedepartment.model.User;
import com.project.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DatabaseManager {

    private UserDao userDao;
    private WorkerDao workerDao;
    private CategoryDao categoryDao;

    @Autowired
    public DatabaseManager(UserDao userDao, WorkerDao workerDao, CategoryDao categoryDao) {
        this.userDao = userDao;
        this.workerDao = workerDao;
        this.categoryDao = categoryDao;
    }

    public List<Worker> getWorkersByRating() {
        return workerDao.getAllByRating();
    }

    public List<Worker> getWorkersByProfession(String profession) {
        return workerDao.getAllByProfession(profession);
    }

    public List<Worker> getWorkersByWorkObject(String workObject) {
        return workerDao.getAllByWorkObject(workObject);
    }

    public List<Worker> getWorkersByNamePart(String namePart) {
        return workerDao.getAllByName(namePart);
    }

    public List<String> getAllProfession() { return categoryDao.getAllProfession(); }

    public List<String> getAllWorkObject() { return categoryDao.getAllWorkObject(); }

    public int getLatestId(String tableName) {
        return userDao.getLatestId(tableName);
    }

    public int registerRegularUser(User user, String hashedPassword) { return userDao.addNewRegularUser(user, hashedPassword);}

    public void registerWorker(int workerId, String phoneNumber, String description) { userDao.addNewWorker(workerId, phoneNumber, description);}

    public User getDataAboutUser(int userId) { return userDao.getDataAboutUser(userId); }

    public boolean checkIfEmailInUse(String email) { return userDao.checkIfUserExists(email); }

    public boolean checkValidLogin(String email, String password) { return userDao.checkIfValidLogin(email, password); }

    public void updateRegularUserData(int userId, String firstName, String lastName, String birthOfDate, String email, String password) {
        userDao.editRegularProfile(userId, firstName, lastName, birthOfDate, email, password);
    }

    public void updateWorker(int userId, String firstName, String lastName, String birthOfDate, String email,
                             String password, String description, String phoneNumber, boolean isAvailable) {
        userDao.editWorkerProfile(userId, firstName, lastName, birthOfDate, email, password, description, phoneNumber, isAvailable);
    }

    public Map<String, Integer> getProfessionWithExperienceOfWorker(int userId) {
        return userDao.getProfessionWithExperience(userId);
    }

    public void saveProfessionWithExperience(int userId, Map<String, Integer> professionAndExperience) {
        userDao.saveProfessionWithExperience(userId, professionAndExperience);
    }

    public int getUserIdByEmail(String email) {
        return userDao.getUserIdByEmail(email);
    }

    public List<User> getAllDataAboutUser() {
        return userDao.getAllDataAboutUser();
    }

    public List<Worker> getAllByFilter(String name, String workObject, String profession, int rating) {
        return workerDao.getAllByFilter(name, workObject, profession, rating);
    }

}
