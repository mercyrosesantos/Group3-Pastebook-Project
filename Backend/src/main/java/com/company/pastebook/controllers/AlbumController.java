package com.company.pastebook.controllers;

import com.company.pastebook.models.Album;
import com.company.pastebook.models.Photo;
import com.company.pastebook.models.Reaction;
import com.company.pastebook.models.User;
import com.company.pastebook.services.AlbumService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@CrossOrigin
public class AlbumController {

    @Autowired
    AlbumService albumService;

    //Create an album
    @RequestMapping(value = "api/user/albums", method = RequestMethod.POST)
    public ResponseEntity<Object> createAlbum(@RequestBody Album album){
        return albumService.createAlbum(album);
    }

    // Add photos to album
    @RequestMapping(value = "api/album/photos", method = RequestMethod.POST)
    ResponseEntity<Object> uploadPhoto (@RequestParam("files") MultipartFile[] file,@RequestParam Long albumId,@RequestParam Long userId)
            throws IOException {
        return albumService.addPhotos(file,albumId,userId);
    }

    //Delete an album
    @RequestMapping(value = "api/user/deleteAlbum", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAlbum(@RequestBody Album album){
        return albumService.deleteAlbum(album);
    }

    //Update an album
    @RequestMapping(value = "api/user/updateAlbum", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAlbum(@RequestBody Album album){
        return albumService.updateAlbum(album);
    }

}
