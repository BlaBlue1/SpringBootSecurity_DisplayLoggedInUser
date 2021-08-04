package com.example.springboot_security403;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void run(String...args){
        User user = new User("bethel", "bethel@domain.com", "bethel", "Bethel", "Sahle", true); //the names and passwords are examples
        Role userRole = new Role("bethel", "ROLE_USER");

        userRepository.save(user);
        roleRepository.save(userRole);


        User admin = new User("elda", "elda@domain.com", "elda", "Elda", "Sahle", true);
        Role adminRole1 = new Role("elda", "ROLE_ADMIN");
        Role adminRole2 = new Role("elsa", "ROLE_USER");

        userRepository.save(admin);
        roleRepository.save(adminRole1);
        roleRepository.save(adminRole2);

    }
}
