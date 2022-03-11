package com.company.pastebook.controllers;

import com.company.pastebook.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AlbumController {

    @Autowired
    AlbumService albumService;

}
