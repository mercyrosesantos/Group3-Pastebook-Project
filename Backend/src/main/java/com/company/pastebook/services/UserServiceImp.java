package com.company.pastebook.services;

import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required=false)
    private User user;

    @Autowired
    private JavaMailSender javaMailSender;

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
    public Iterable<String> verifyUser(String email){
        ArrayList<String> key = new ArrayList<>();
        key.add(userRepository.findByEmail(email).getPassword());
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
        String fromAddress = "aldrianblanquera000@gmail.com";
        String senderName = "Pastebook Development Team";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Pastebook Development Team.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstName());
        String verifyURL = siteUrl + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        javaMailSender.send(message);
    }


}
