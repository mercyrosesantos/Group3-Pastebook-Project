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
         ArrayList<String> userFound = new ArrayList<>();
         ArrayList<ArrayList<String>> searchResult = new ArrayList<>();
         for(User user: userRepository.findAll()){
             if (user.getFirstName().equalsIgnoreCase(keyword)||user.getLastName().equalsIgnoreCase(keyword)){
                 userFound.add(user.getFirstName() + " " + user.getLastName());
                 // userFound.add(user.getProfileLink());
                 searchResult.add(userFound);
             }
         }
         if (searchResult.isEmpty()){
             return new ResponseEntity("No user found.", HttpStatus.BAD_REQUEST);
         }
         return new ResponseEntity(searchResult, HttpStatus.OK);
    }
}
