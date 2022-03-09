package com.company.pastebook.services;

import com.company.pastebook.models.Reaction;
import com.company.pastebook.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImp implements ReactionService {

    @Autowired
    ReactionRepository reactionRepository;

    //Create Reaction
    public ResponseEntity createReaction(Reaction reaction) {
        reactionRepository.save(reaction);
        return new ResponseEntity<>("Reaction Recorded",HttpStatus.CREATED);
    }
}
