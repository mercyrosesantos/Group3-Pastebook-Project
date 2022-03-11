package com.company.pastebook.controllers;

import com.company.pastebook.models.Album;
import com.company.pastebook.models.Photo;
import com.company.pastebook.models.User;
import com.company.pastebook.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FileUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
public class PhotoController {

    @Autowired
    PhotoService photoService;

    // Upload a Photo
    @RequestMapping(value = "api/user/photos/", method = RequestMethod.POST)
    ResponseEntity<Object> uploadPhoto (@RequestParam("file") MultipartFile file,@RequestParam(required=false) String caption ,
                                        @RequestParam(required=false) Long userId,@RequestParam(required=false) Long albumId)
            throws IOException {
        Photo photo = new Photo();
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getName());
        file.transferTo(convFile);
        byte[] bytes = FileUtils.readFileToByteArray(convFile);

        if (userId != null) {
            User newUser = new User();
            newUser.setId(userId);
            photo.setUser(newUser);
        }
        if (albumId != null) {
            Album newAlbum = new Album();
            newAlbum.setId(albumId);
            photo.setAlbum(newAlbum);

        }

        photo.setImage(bytes);
        photo.setCaption(caption);
        return new ResponseEntity<>(photoService.uploadPhoto(photo), HttpStatus.CREATED);
    }

    //Get Photo
    @RequestMapping(value = "/api/photo/{photoId}", method=RequestMethod.GET)
    public ResponseEntity<Object> getPhoto(@PathVariable long photoId) {
        return photoService.getPhoto(photoId);
    }
}
