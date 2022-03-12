package com.company.pastebook.services;

import com.company.pastebook.models.Photo;
import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImp implements PhotoService{

    @Autowired
    PhotoRepository photoRepository;

    //Upload a photo
    public ResponseEntity uploadPhoto (Photo photo){
        photoRepository.save(photo);
        return new ResponseEntity("Photo uploaded.", HttpStatus.OK);
    }

    //Get photo
    public ResponseEntity getPhoto(Long photoId){
        Photo userPhoto = photoRepository.findById(photoId).get();
        return new ResponseEntity(userPhoto, HttpStatus.OK);
    }
}
