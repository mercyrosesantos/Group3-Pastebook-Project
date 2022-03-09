package com.company.pastebook.controllers;

import com.company.pastebook.models.User;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.services.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@JsonFormat(pattern="yyyy-MM-dd")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository user;

    @Autowired
    User userModel;

    //    Register User
    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User newUser, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        HashMap<String, String> response = new HashMap<>();
        LocalDate today = LocalDate.now();
        String strDate = today.toString();
        String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        String email = newUser.getEmail();
        String randomCode = RandomString.make(64);

        if (!userService.findByEmail(email).isPresent()) {
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
            userModel.setVerificationCode(randomCode);
            userModel.setEnabled(false);
            userService.sendVerificationEmail(newUser, siteUrl);

            response.put("Result", "Added");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("Result", "User Exist");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
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

    // Get User Profile
    @RequestMapping(value = "/api/profile/{userId}", method=RequestMethod.GET)
    public ResponseEntity<Object> getUserProfile(@PathVariable long userId) {
        return userService.getUserProfile(userId);
    }
}
