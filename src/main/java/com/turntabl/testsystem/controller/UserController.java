package com.turntabl.testsystem.controller;

import com.turntabl.testsystem.dao.UserDAO;
import com.turntabl.testsystem.helper.StringToUserIdConverter;
import com.turntabl.testsystem.message.GeneralAddResponse;
import com.turntabl.testsystem.message.UserRequest;
import com.turntabl.testsystem.message.UserResponse;
import com.turntabl.testsystem.model.Role;
import com.turntabl.testsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final StringToUserIdConverter stringToUserIdConverter;

    public UserController(UserDAO userDAO, StringToUserIdConverter stringToUserIdConverter) {
        this.userDAO = userDAO;
        this.stringToUserIdConverter = stringToUserIdConverter;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        try {
            List<UserResponse> users = userDAO.getAll().stream()
                    .map(user -> {
                        UserResponse userResponse = new UserResponse();
                        userResponse.setUser_id(user.getUser_id());
                        userResponse.setEmail(user.getEmail());
                        userResponse.setFirst_name(user.getFirst_name());
                        userResponse.setLast_name(user.getLast_name());
                        userResponse.setRole(user.getRole());
                        return userResponse;
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String user_id){
        try {
            Optional<User>  user = userDAO.get(stringToUserIdConverter.convert(user_id));
            if(user.isPresent()){
                UserResponse userResponse = new UserResponse();
                userResponse.setUser_id(user.get().getUser_id());
                userResponse.setEmail(user.get().getEmail());
                userResponse.setFirst_name(user.get().getFirst_name());
                userResponse.setLast_name(user.get().getLast_name());
                userResponse.setMessage("yes");
                userResponse.setRole(user.get().getRole());
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new UserResponse("no", null, null, null, null, 0), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(e.getMessage(), null, null, null, null, 0), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user-by-email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
        try {
            Optional<User> user = userDAO.getByEmail(email);
            if(user.isPresent()){
                UserResponse userResponse = new UserResponse();
                userResponse.setUser_id(user.get().getUser_id());
                userResponse.setEmail(user.get().getEmail());
                userResponse.setFirst_name(user.get().getFirst_name());
                userResponse.setLast_name(user.get().getLast_name());
                userResponse.setMessage("yes");
                userResponse.setRole(user.get().getRole());
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new UserResponse("no", null, null, null, null, 0), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(e.getMessage(), null, null, null, null, 0), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/add")
    public ResponseEntity<GeneralAddResponse> addUser(@RequestBody UserRequest userRequest) {
        try{
            User user = new User();
            user.setEmail(userRequest.getEmail());
            user.setFirst_name(userRequest.getFirst_name());
            user.setLast_name(userRequest.getLast_name());
            if(userRequest.getRole() == 1){
                user.setRole(Role.SUPER_ADMIN.getCode());
            }else if (userRequest.getRole() == 2){
                user.setRole(Role.ADMIN.getCode());
            }
            user = userDAO.add(user);

            if(user.getUser_id() != null){
                return new ResponseEntity<>(new GeneralAddResponse("success"), HttpStatus.OK);
            }

            return new ResponseEntity<>(new GeneralAddResponse("user already exist"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete-user/{user_id}")
    public ResponseEntity<GeneralAddResponse> deleteUser(@PathVariable String user_id){
        Optional<User> user = userDAO.get(stringToUserIdConverter.convert(user_id));
        try {
            return new ResponseEntity<>(userDAO.delete(user.get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new GeneralAddResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
