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

//    Get online friends
    ResponseEntity getOnlineFriends(Long userId);

//    Get User Profile
    ResponseEntity getUserProfile(Long id);

//    Get User Profile by URL
    ResponseEntity getUserProfileByUrl(String url);



//    Send Verification Email
    void sendVerificationEmail(User user, String siteUrl) throws MessagingException, UnsupportedEncodingException;

    // Get user by id
    ResponseEntity getUser(Long id);
//    Update AboutMe
    ResponseEntity updateAboutMe(String aboutMe, Long userId);
}
