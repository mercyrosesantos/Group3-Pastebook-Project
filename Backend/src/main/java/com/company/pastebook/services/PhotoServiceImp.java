package com.company.pastebook.services;

import com.company.pastebook.models.Photo;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.PhotoRepository;
import com.company.pastebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImp implements PhotoService{

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    UserRepository userRepository;

    //Upload a photo
    public ResponseEntity uploadPhoto (Photo photo){
        Photo newPhoto = photoRepository.save(photo);
        User user = userRepository.findById(photo.getUser().getId()).orElse(null);
        user.setPhoto(newPhoto);
        userRepository.save(user);
        return new ResponseEntity("Photo uploaded.", HttpStatus.OK);
    }

    //Get photo
    public ResponseEntity getPhoto(Long photoId){
        Photo userPhoto = photoRepository.findById(photoId).get();
        return new ResponseEntity(userPhoto, HttpStatus.OK);
    }
}
