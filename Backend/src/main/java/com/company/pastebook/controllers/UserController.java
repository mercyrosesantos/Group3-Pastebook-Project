package com.company.pastebook.controllers;

import com.company.pastebook.Constants;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.services.ReactionService;
import com.company.pastebook.services.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    ReactionService reactionService;

    //    Register User
    @RequestMapping(value = "/api/users/register", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User newUser) throws MessagingException, UnsupportedEncodingException {
        HashMap<String, String> response = new HashMap<>();

        LocalDate today = LocalDate.now();
        String strDate = today.toString();
        String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        String email = newUser.getEmail();
        String randomCode = RandomString.make(64);
        newUser.setPassword(encodedPassword);
        newUser.setDateJoined(strDate);
        newUser.setVerificationCode(randomCode);

        if (!userService.findByEmail(email).isPresent()) {
            user.save(newUser);
            String siteUrl = "http://localhost:8080";
            userService.sendVerificationEmail(newUser, siteUrl);

            response.put("Result", "Added");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("Result", "User Exist");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
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
                response.put("firstName", newUser.getFirstName());
                response.put("lastName", newUser.getLastName());
                response.put("id", newUser.getId().toString());
                response.put("token", generateToken(newUser.getId(), newUser.getEmail()));
                return new ResponseEntity<>(response , HttpStatus.OK);
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

    // Generate token
    private String generateToken(Long id, String email) {
        long timestamp = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY);

        builder = builder.setIssuedAt(new Date(timestamp));
        builder = builder.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY));

        builder = builder.claim("id", id);
        builder = builder.claim("email", email);

        return builder.compact();
    }

    // Get online friends
    @RequestMapping ( value = "/api/online/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOnlineFriends(@PathVariable Long userId) {
        return userService.getOnlineFriends(userId);
    }
    
}
