package com.company.pastebook.services;

import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

//    Register User
    public void createUser(User user) {
        userRepository.save(user);
    }

//    Get all Users
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
