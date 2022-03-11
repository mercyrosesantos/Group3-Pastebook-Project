package com.company.pastebook.services;

import com.company.pastebook.models.Album;
import org.springframework.http.ResponseEntity;

public interface AlbumService {

    //Create an album
    ResponseEntity createAlbum (Album album);
}
