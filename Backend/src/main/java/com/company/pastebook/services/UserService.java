package com.company.pastebook.services;

import com.company.pastebook.models.User;

import java.util.Optional;

public interface UserService {
//    Register user
    void createUser(User user);

//    Find by email
    Optional<User> findByEmail(String email);

//    Login user
    Iterable<String> verifyUser(String email);



}
