package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.model.User;
import com.turntabl.testsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAO {
    @Autowired
    private UserRepository userRepository;

    public UserDAO() {
    }

    public Optional<User> get(UUID user_id){
        return userRepository.findById(user_id);
    }
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public User add(User user) {
        return userRepository.save(user);
    }
    public User update(User user){
        User user1 = userRepository.findById(user.getUser_id()).get();
        user1.setEmail(user.getEmail());
        user1.setRole(user.getRole());
        return  userRepository.save(user1);
    }
    public boolean delete(User user) {
        boolean isDeleted = false;
        Optional<User> optionalUser = userRepository.findById(user.getUser_id());
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            isDeleted = true;
        }
        return isDeleted;
    }
}
