package com.company.pastebook.controllers;

import com.company.pastebook.models.User;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.services.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@JsonFormat(pattern="yyyy-MM-dd")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository user;

    //    Register User
    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User newUser) {
        HashMap<String, String> response = new HashMap<>();
        LocalDate today = LocalDate.now();
        String strDate = today.toString();
        String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());

        user.save(new User(
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                encodedPassword,
                newUser.getBirthDay(),
                newUser.getGender(),
                newUser.getMobileNumber(),
                strDate
        ));

        response.put("Result", "Added");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //    Login
    @RequestMapping(value = "/api/users/login", method = RequestMethod.POST)
    public ResponseEntity<Object> verifyUser(@RequestBody Map<String, String> body){
        HashMap<String, String> response = new HashMap<>();
        String email = body.get("email");
        User newUser = user.findByEmail(email);
        if (newUser != null){
            String input =  body.get("password");

            boolean isMatched = new BCryptPasswordEncoder().matches(input, newUser.getPassword());
            if (isMatched) {
                response.put("result", "successful");
                response.put("email", newUser.getEmail());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                response.put("result", "incorrect_credentials");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else{
            response.put("result", "user_not_found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Get User Profile
    @RequestMapping(value = "/api/profile/{userId}", method=RequestMethod.GET)
    public ResponseEntity<Object> getUserProfile(@PathVariable long userId) {
        return userService.getUserProfile(userId);
    }
}
