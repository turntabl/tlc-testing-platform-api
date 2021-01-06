package com.turntabl.testsystem.service;

import com.turntabl.testsystem.dao.UserDAO;
import com.turntabl.testsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadData implements ApplicationRunner {
    @Autowired
    private final UserDAO userDAO;

    public LoadData(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private void loadUserData() {
        if (!userDAO.getByEmail("sam@turntabl.io").isPresent()) {
            List<User> userList = new ArrayList<>();
            User user = new User("sam@turntabl.io", "Sam", "Moorhouse", 1);
            userList.add(user);
            User user1 = new User("emmanuel.bonsu@turntabl.io", "Emmanuel", "Bonsu", 1);
            userList.add(user1);
            User user2 = new User("naa.dsane@turntabl.io", "Naa", "Dsane", 1);
            userList.add(user2);
            User user3 = new User("humaidu.ali@turntabl.io", "Humaidu", "Ali", 1);
            userList.add(user3);
            User user4 = new User("nicholas.dickson@turntabl.io", "Nicholas", "Dickson", 1);
            userList.add(user4);
            User user5 = new User("david.andoh-acquah@turntabl.io", "David", "Andoh", 1);
            userList.add(user5);
            userDAO.addAll(userList);
            System.out.println("users added");
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUserData();
    }
}
