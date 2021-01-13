package com.turntabl.testsystem.dao;

import com.turntabl.testsystem.message.GeneralAddResponse;
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
        User user1 = new User();
        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            user1 = userRepository.save(user);
        }
        return user1;
    }
    public List<User> addAll(List<User> users) {
        return userRepository.saveAll(users);
    }
    public User update(User user){
        User user1 = userRepository.findById(user.getUser_id()).get();
        user1.setEmail(user.getEmail());
        user1.setRole(user.getRole());
        return  userRepository.save(user1);
    }
    public GeneralAddResponse delete(User user) {
        GeneralAddResponse generalAddResponse = new GeneralAddResponse();
        Optional<User> optionalUser = userRepository.findById(user.getUser_id());
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            generalAddResponse.setMessage("success");
        }else{
            generalAddResponse.setMessage("failed");
        }
        return generalAddResponse;
    }
}
