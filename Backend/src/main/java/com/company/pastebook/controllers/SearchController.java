package com.company.pastebook.controllers;

import com.company.pastebook.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    SearchService searchService;

    // Search user
    @RequestMapping(value = "/api/search", method = RequestMethod.GET)
    public ResponseEntity<Object> searchUser(@RequestBody Map<String, String> body){
        return searchService.searchUser(body.get("keyword"));
    }
}
