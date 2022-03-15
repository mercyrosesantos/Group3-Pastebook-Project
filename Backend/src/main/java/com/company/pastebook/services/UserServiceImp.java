package com.company.pastebook.services;

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

//    Update User Information
    public ResponseEntity updateUserInfo(Long id, User user) {
        User userForUpdating = userRepository.findById(id).get();
        userForUpdating.setFirstName(user.getFirstName());
        userForUpdating.setLastName(user.getLastName());
        userForUpdating.setBirthDay(user.getBirthDay());
        userForUpdating.setGender(user.getGender());
        userRepository.save(userForUpdating);
        return new ResponseEntity("User Updated Successfully", HttpStatus.OK);
    }

//    Update User Email
    public ResponseEntity updateUserEmail(Long id, User user) {
        User userForUpdating = userRepository.findById(id).get();
        userForUpdating.setEmail(user.getEmail());
        userRepository.save(userForUpdating);
        return new ResponseEntity("Email Updated Successfully", HttpStatus.OK);
    }

//        Update User Password
    public ResponseEntity updateUserPassword(Long id, User user) {
        User userForUpdating = userRepository.findById(id).get();
        userForUpdating.setPassword(user.getPassword());
        userRepository.save(userForUpdating);
        return new ResponseEntity("Password Updated Successfully", HttpStatus.OK);
    }

}
