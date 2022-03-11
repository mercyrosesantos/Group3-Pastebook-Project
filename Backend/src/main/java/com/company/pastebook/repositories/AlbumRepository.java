package com.company.pastebook.repositories;

import com.company.pastebook.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Object> {
}
