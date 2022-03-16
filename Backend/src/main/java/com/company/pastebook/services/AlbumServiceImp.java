package com.company.pastebook.services;

import com.company.pastebook.models.Album;
import com.company.pastebook.models.Photo;
import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.AlbumRepository;
import com.company.pastebook.repositories.PhotoRepository;
import com.company.pastebook.repositories.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class AlbumServiceImp implements AlbumService  {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    UserRepository userRepository;

    //Create an album
    public ResponseEntity createAlbum (Album album){
        albumRepository.save(album);
        return new ResponseEntity("Album created.", HttpStatus.OK);
    }

    //Add Photos to album
    public ResponseEntity addPhotos(MultipartFile[] files, Long userId, Long albumId) throws IOException {

        ArrayList<Photo> photos = new ArrayList<>();

        for(MultipartFile file : files) {

            Photo photo = new Photo();
            File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getName());
            file.transferTo(convFile);
            byte[] bytes = FileUtils.readFileToByteArray(convFile);

            if (userId != null) {
                User newUser = userRepository.findById(userId).orElse(null);
                photo.setUser(newUser);
            }
            if (albumId != null) {
                Album newAlbum = new Album();
                newAlbum.setId(albumId);
                photo.setAlbum(newAlbum);

            }

            photo.setImage(bytes);
            photos.add(photo);
        }
        photoRepository.saveAll(photos);
        return new ResponseEntity<>("Photos has been saved", HttpStatus.CREATED);
    }

    //Delete an Album
    public ResponseEntity deleteAlbum (Album album) {
        albumRepository.delete(album);
        return new ResponseEntity("Album Deleted.", HttpStatus.OK);
    }

    //Update Album
    public ResponseEntity updateAlbum (Album album) {
        albumRepository.save(album);
        return new ResponseEntity("Album Updated.", HttpStatus.OK);
    }
}
