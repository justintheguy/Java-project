package com.project.forcedepartment.service;

import com.project.forcedepartment.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    //Dummy, it will be with DAO

    private List<User> test = new ArrayList<>();
    private boolean userIsExist;

    public void addUserTest(User user) {
        test.add(user);
    }

    public void getAllInformation() {
        for (User user: test) {
            System.out.println(user.toString());
        }
    }

    public void checkUserByEmailAndPassword(String email, String password) {

        for (User user: test) {
            if (user.getEmail().equals(email)) {
                if(user.getPassword().equals(password)) {
                    setUserIsExist(true);
                }
            }
            else {
                setUserIsExist(false);
            }
        }
    }

    public boolean isUserIsExist() {
        return userIsExist;
    }

    public void setUserIsExist(boolean userIsExist) {
        this.userIsExist = userIsExist;
    }
}
