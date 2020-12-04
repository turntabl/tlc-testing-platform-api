//package com.turntabl.testsystem.dao;//package com.turntabl.Client_Connectivity.auth.dao;
//
//
//import com.turntabl.testsystem.model.Student;
//import com.turntabl.testsystem.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//
//@Component
//public class LoadDatabase implements ApplicationRunner {
//
//    private final StudentRepository userRepository;
//
//    @Autowired
//    public LoadDatabase(StudentRepository repository){
//        this.userRepository = repository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Student admin = new Student();
//        System.out.println(userRepository.save(admin).getStudent_id());
//    }
//}
