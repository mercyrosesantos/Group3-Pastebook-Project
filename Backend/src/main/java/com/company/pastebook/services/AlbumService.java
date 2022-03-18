package com.company.pastebook.services;

import com.company.pastebook.models.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlbumService {

    //Create an album
    ResponseEntity createAlbum (Album album);

    //Add Photos to Album
    ResponseEntity addPhotos(MultipartFile[] files, Long userId, Long albumId) throws IOException;

    //Delete an Album
    ResponseEntity deleteAlbum (Long Id);

    //Update Album
    ResponseEntity updateAlbum (Album album);

    //Get album per User
    ResponseEntity getAlbum(Long userId);

    //Get Album by AlbumID
    ResponseEntity getAlbumById(Long albumId);



}
