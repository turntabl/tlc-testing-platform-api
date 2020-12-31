package com.turntabl.testsystem.service;

import com.turntabl.testsystem.dao.UserDAO;
import com.turntabl.testsystem.model.Role;
import com.turntabl.testsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements ApplicationRunner {
    @Autowired
    private final UserDAO userDAO;

    public LoadData(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private void loadUserData() {
        System.out.println(Role.SUPER_ADMIN);
        System.out.println(Role.ADMIN);
        if (!userDAO.getByEmail("sam@turntabl.io").isPresent()) {
            User user = new User();
            user.setEmail("sam@turntabl.io");
            user.setRole(Role.SUPER_ADMIN.getCode());
            userDAO.add(user);
            System.out.println("user added");
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUserData();
    }
}
