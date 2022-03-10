package com.company.pastebook.controllers;

import com.company.pastebook.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    SearchService searchService;

    // Search user
    @RequestMapping(value = "/api/search/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchUser(@PathVariable String keyword){
        return searchService.searchUser(keyword);
    }
}
