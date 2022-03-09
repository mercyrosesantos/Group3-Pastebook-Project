package com.company.pastebook.services;

import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity searchUser(String keyword);
}
