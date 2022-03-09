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
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<Object> verifyUser(@RequestBody Map<String, String> body){
        String email = body.get("email");
        if (userService.findByEmail(email).isPresent()){
            ArrayList<String> input = new ArrayList<>();
            input.add(body.get("password"));
            if (userService.verifyUser(email).equals(input)) {
                return new ResponseEntity<>("User successfully logged-in", HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>("Invalid credentials.", HttpStatus.BAD_REQUEST);
            }
        } else{
            return new ResponseEntity<>("User does not exist.", HttpStatus.BAD_REQUEST);
        }
    }
}
