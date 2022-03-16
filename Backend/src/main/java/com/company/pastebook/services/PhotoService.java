package com.company.pastebook.services;

import com.company.pastebook.models.Photo;
import org.springframework.http.ResponseEntity;

public interface PhotoService {

    //upload a photo
    ResponseEntity uploadPhoto (Photo photo);

    //Get a photo
    ResponseEntity getPhoto(Long photoId);
}
