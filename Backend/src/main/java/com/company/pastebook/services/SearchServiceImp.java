package com.company.pastebook.services;

import com.company.pastebook.models.User;
import com.company.pastebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    UserRepository userRepository;

    // Display all matches (users and posts)
     public ResponseEntity<Object> searchUser(String keyword){
         ArrayList<User> userFound = new ArrayList<>();
         ArrayList<ArrayList<User>> searchResult = new ArrayList<>();
         for(User user: userRepository.findAll()){
             if (user.getFirstName().equalsIgnoreCase(keyword)||user.getLastName().equalsIgnoreCase(keyword)) {
                 userFound.add(user);
                 // userFound.add(user.getFirstName() + " " + user.getLastName());
                 // userFound.add(user.getProfileLink());
                 searchResult.add(userFound);
             }
         }
         if (userFound.isEmpty()){
             return new ResponseEntity("No user found.", HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity(searchResult.get(0), HttpStatus.OK);
    }
}
