package com.company.pastebook.repositories;

import com.company.pastebook.models.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Object> {
//    Find user by email
    @Query(value = "SELECT * FROM users WHERE email = ?1 LIMIT 1", nativeQuery = true)
    User findByEmail(String email);


}
