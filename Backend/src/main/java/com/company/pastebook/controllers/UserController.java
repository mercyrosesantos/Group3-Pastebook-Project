package com.company.pastebook.controllers;

import com.company.pastebook.models.User;
import com.company.pastebook.services.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

//    Register User
    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
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
