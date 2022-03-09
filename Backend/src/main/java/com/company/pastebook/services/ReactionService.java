package com.company.pastebook.services;

import com.company.pastebook.models.Reaction;
import org.springframework.http.ResponseEntity;

public interface ReactionService {

    //Create Reaction
    ResponseEntity createReaction(Reaction reaction);
}
