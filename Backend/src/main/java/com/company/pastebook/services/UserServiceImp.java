package com.company.pastebook.services;

import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

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

//    Find by email
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

//    Login
    public Iterable<String> verifyUser(String email){
        ArrayList<String> key = new ArrayList<>();
        key.add(userRepository.findByEmail(email).getPassword());
        return key;
    }

// Get User Profile
    public ResponseEntity getUserProfile(Long id){
        User userProfile = userRepository.findById(id).get();
        return new ResponseEntity(userProfile, HttpStatus.OK);
}


}
