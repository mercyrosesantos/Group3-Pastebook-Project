package com.company.pastebook.repositories;

import com.company.pastebook.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Object> {

    // Find user by email
    User findByEmail(String email);

    // Find user by url
    User findByUrl(String url);


}
