package com.company.pastebook.controllers;

import com.company.pastebook.models.Reaction;
import com.company.pastebook.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReactionController {

    @Autowired
    ReactionService reactionService;

    //Create Reaction
    @RequestMapping(value="/reactions", method= RequestMethod.POST)
    public ResponseEntity<Object> createReaction(@RequestBody Reaction reaction){
        System.out.println(reaction);
        return reactionService.createReaction(reaction);
    }

}
