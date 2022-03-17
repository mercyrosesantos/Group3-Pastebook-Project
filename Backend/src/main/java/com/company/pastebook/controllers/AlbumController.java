package com.company.pastebook.controllers;

import com.company.pastebook.models.Album;
import com.company.pastebook.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@CrossOrigin
public class AlbumController {

    @Autowired
    AlbumService albumService;

    //Create an album
    @RequestMapping(value = "api/user/albums", method = RequestMethod.POST)
    ResponseEntity<Object> createAlbum(@RequestBody Album album){
        return albumService.createAlbum(album);
    }

    // Add photos to album
    @RequestMapping(value = "api/album/photos", method = RequestMethod.POST)
    ResponseEntity<Object> uploadPhoto (@RequestParam("files[]") MultipartFile[] files,@RequestParam Long albumId,@RequestParam Long userId)
            throws IOException {
        return albumService.addPhotos(files,userId,albumId);
    }

    //Delete an album
    @RequestMapping(value = "api/user/deleteAlbum/{albumId}", method = RequestMethod.DELETE)
    ResponseEntity<Object> deleteAlbum(@PathVariable Long albumId){
        return albumService.deleteAlbum(albumId);
    }

    //Update an album
    @RequestMapping(value = "api/user/updateAlbum", method = RequestMethod.PUT)
    ResponseEntity<Object> updateAlbum(@RequestBody Album album){
        return albumService.updateAlbum(album);
    }

    //Get album per Use
    @RequestMapping(value = "api/album/{userId}", method = RequestMethod.GET)
    ResponseEntity<Object> getAlbum(@PathVariable Long userId) {
        return albumService.getAlbum(userId);
    }

    //Get Album by AlbumID
    @RequestMapping(value = "api/albums-view/{albumId}", method = RequestMethod.GET)
    ResponseEntity<Object> getAlbumById(@PathVariable Long albumId) {
        return albumService.getAlbumById(   albumId);
    }
}
