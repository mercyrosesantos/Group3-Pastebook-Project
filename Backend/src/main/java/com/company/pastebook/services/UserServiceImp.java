package com.company.pastebook.services;

import com.company.pastebook.models.Friendship;
import com.company.pastebook.repositories.FriendshipRepository;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

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

    // Get User Profile by Url
    public ResponseEntity getUserProfileByUrl(String url){
        User userProfileByUrl = userRepository.findByUrl(url);
        return new ResponseEntity(userProfileByUrl, HttpStatus.OK);
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
        userForUpdating.setMobileNumber(user.getMobileNumber());
        userForUpdating.setUrl(user.getFirstName().toLowerCase()+user.getLastName().toLowerCase()+"-"+user.getId());
        userRepository.save(userForUpdating);
        return new ResponseEntity("User updated successfully.", HttpStatus.OK);
    }

    //    Update User Email
    public ResponseEntity updateUserEmail(Long id, User user) {
        User userForUpdating = userRepository.findById(id).get();

        if(findByEmail(user.getEmail()).isPresent()) {
            return new ResponseEntity("Email already exists.", HttpStatus.BAD_REQUEST);
        }
        userForUpdating.setEmail(user.getEmail());
        boolean isMatched = new BCryptPasswordEncoder().matches(user.getPassword(), userForUpdating.getPassword());
        if (!isMatched) {
            return new ResponseEntity("Incorrect password.", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(userForUpdating);
        return new ResponseEntity("Email updated successfully.", HttpStatus.OK);
    }

    //        Update User Password
    public ResponseEntity updateUserPassword(Long id, Map<String, String> body) {
        User userForUpdating = userRepository.findById(id).get();
         String oldPassword = new BCryptPasswordEncoder().encode(body.get("oldPassword"));
        String encodedPassword = new BCryptPasswordEncoder().encode(body.get("newPassword"));


        boolean isMatched = new BCryptPasswordEncoder().matches(body.get("oldPassword"), userForUpdating.getPassword());
        String newPassword = body.get("newPassword");
        String retypePassword = body.get("retypePassword");
        if (!isMatched) {
            return new ResponseEntity("Old password provided is incorrect.", HttpStatus.BAD_REQUEST);
        }
        if (!newPassword.equals(retypePassword)) {
            return new ResponseEntity("Password does not match.", HttpStatus.BAD_REQUEST);
        }
        userForUpdating.setPassword(encodedPassword);
        userRepository.save(userForUpdating);
        return new ResponseEntity("Password updated successfully.", HttpStatus.OK);
    }

    // Get online friends
    public ResponseEntity<Object> getOnlineFriends(Long userId){
        ArrayList<User> friends = new ArrayList<>();
        for (Friendship friend: friendshipRepository.findAll()){
            if (friend.getUser().getId().equals(userId)){
               if (friend.getFriend().isActive()) {
                   friends.add(friend.getFriend());
               }
            }
        }
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }


    // Get user by id
    public ResponseEntity<Object> getUser(Long id) {
        ArrayList<User> userArray = new ArrayList<>();
        User getUser = userRepository.findById(id).get();
        userArray.add(getUser);
        return new ResponseEntity<>(userArray, HttpStatus.OK);
    }

    //Update AboutMe
    public ResponseEntity updateAboutMe(String aboutMe, Long userID){
        User userAboutMe = userRepository.findById(userID).orElse(null);
        if (userAboutMe == null) {
            return new ResponseEntity("No User Found.", HttpStatus.CONFLICT);
        }
        userAboutMe.setAboutMe(aboutMe);
        userRepository.save(userAboutMe);
        return new ResponseEntity("About Me created.", HttpStatus.OK);
    }
}
