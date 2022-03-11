package com.company.pastebook.services;

import com.company.pastebook.models.Album;
import com.company.pastebook.models.Photo;
import com.company.pastebook.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImp implements AlbumService  {

    @Autowired
    AlbumRepository albumRepository;

    //Create an album
    public ResponseEntity createAlbum (Album album){
        albumRepository.save(album);
        return new ResponseEntity("Album created.", HttpStatus.OK);
    }
}
