package com.company.pastebook.services;

import com.company.pastebook.models.Friendship;
import com.company.pastebook.repositories.FriendshipRepository;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired(required=false)
    private User user;

    @Autowired
    private JavaMailSenderImpl mailSenderImpl;

//    Register User
    public void createUser(User user, String siteUrl) {
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
    public Iterable<User> verifyUser(String email){
        ArrayList<User> key = new ArrayList<>();
        key.add(userRepository.findByEmail(email));
        return key;
    }

// Get User Profile
    public ResponseEntity getUserProfile(Long id){
        User userProfile = userRepository.findById(id).get();
        return new ResponseEntity(userProfile, HttpStatus.OK);
    }

//    User Verification
    public boolean isEnabled() {
        return user.isEnabled();
    }

//    Sending Verification Email
    public void sendVerificationEmail(User user, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "pastebook.group3@gmail.com";
        String senderName = "Pastebook Development Team";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Pastebook Development Team.";

        MimeMessage message = mailSenderImpl.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = siteUrl + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSenderImpl.setUsername("pastebook.group3@gmail.com");
        mailSenderImpl.setPassword("Pastebook1!");
        mailSenderImpl.send(message);
    }

    // Get online friends
    public ResponseEntity<Object> getOnlineFriends(Long userId){
        ArrayList<User> friends = new ArrayList<>();
        for (Friendship friend: friendshipRepository.findAll()){
            if (friend.getUserId().equals(userId)){
               if (friend.getFriend().isActive()) {
                   friends.add(friend.getFriend());
               }
            }
        }
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    // Get user by id
    public ResponseEntity<Object> getUser(Long id) {
        User getUser = userRepository.findById(id).get();
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }
}
