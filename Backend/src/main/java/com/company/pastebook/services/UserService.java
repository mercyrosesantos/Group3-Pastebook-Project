package com.company.pastebook.services;

import com.company.pastebook.models.User;
import org.springframework.http.ResponseEntity;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface UserService {
//    Register user
    void createUser(User user, String siteUrl);

//    Find by email
    Optional<User> findByEmail(String email);

//    Login user
    Iterable<User> verifyUser(String email);

// Get User Profile
    ResponseEntity getUserProfile(Long id);

//    Send Verification Email
    void sendVerificationEmail(User user, String siteUrl) throws MessagingException, UnsupportedEncodingException;

//    Update User Information
    ResponseEntity updateUserInfo(Long id, User user);

//    Update User Email
    ResponseEntity updateUserEmail(Long id, User user);

//    Update User Password
    ResponseEntity updateUserPassword(Long id, User user);
}




